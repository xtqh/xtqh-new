$(function() {
	// 加载流程应用系统
	$.ajax({
		url : "resourcemanager!getApplicationIsCitation.action",
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.length != 0) {
				$.each(data, function(i, d) {
					$("#appNameInCmdb").append(
							"<option value='" + d.appNameInCmdb + "'>"
									+ d.appNameInCmdb + "</option>");
				})
			}
		}
	});
	// //////////////
	$("#blur").keydown(function(event) {
		if (event.keyCode == 13) {
			queryResource(1, 10);
			return false;
		}
	});

	queryResource(1, 10);

	// 资源类型
	$.post("sysitem!getResType.action", function(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$("#type").append(
						"<option value='" + data[i].id + "'>" + data[i].value
								+ "</option>");
			}
		}
	});
	// 资源地点
	$.post("sysitem!getResLocation.action", function(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$("#location").append(
						"<option value='" + data[i].id + "'>" + data[i].value
								+ "</option>");
			}
		}
	});

	// 获取可达资源数
	$.post("ansresourcestatus!getCount.action?ping_status=1", {},
			function(data) {
				if (data.result) {
					$("#ans_resource1").text(data.c);
				}
			}, "json");

	// 不可达资源数
	$.post("ansresourcestatus!getCount.action?ping_status=0", {},
			function(data) {
				if (data.result) {
					$("#ans_resource0").text(data.c);
				}
			}, "json");

	$("#ans_resource0").click(function() {

		loadFrame("resourcemanager!toInaccessPage.action");
	})

	$("#btnDelMany").click(function() {
		var ids = [];
		var cpage = $("#cpage").val();
		$("input[name='arrid[]']").each(function() {
			if ($(this).prop('checked')) {
				ids.push($(this).val());
			}
		});
		if (ids.length == 0) {
			layer.msg("请选择要删除的资源");
			return false;
		}
		layer.confirm('确定要删除吗？', {
			btn : [ '是', '否' ]
		// 按钮
		}, function(index) {
			var index1 = layer.load(1, {
				shade : [ 0.3 ]
			// 0.1透明度的白色背景
			});
			$.post("resourcemanager!deleteResourceMany.action", {
				"ids[]" : ids
			}, function(data) {
				setTimeout(function() {
					layer.closeAll('loading'); // 关闭加载层
					if (data.result) {
						layer.close(index);
						for (i = 0; i < ids.length; i++) {
							$("tr[id='" + ids[i] + "']").remove();
						}
						queryResource(cpage, 10);
					} else {
						layer.msg(data.message);
					}
				}, 1000);
			}, "json");
		}, function() {

		});
	});

	// 新建资源
	$("#newresource").click(function() {
		// 保存时弹出层防止重复点击
		var index1 = layer.load(1, {
			shade : [ 0.3 ]
		// 0.1透明度的白色背景
		});

		setTimeout(function() {
			loadFrame("resourcemanager!toAddPage.action");
		}, 1000);
	})

	$("#btnBatchCopy")
			.click(
					function() {
						var index1 = layer.load(1, {
							shade : [ 0.3 ]
						// 0.1透明度的白色背景
						});
						// //
						setTimeout(
								function() {
									layer.closeAll('loading'); // 关闭加载层
									// 加载cmdb资源
									var index = layer
											.open({
												type : 2,
												title : '批量复制CMDB资源',
												shade : 0.8,
												shadeClose : true,
												area : [ '70%', '85%' ],
												content : [
														"resourcemanager!toBatchCmdbPage.action?&callback=finishSelCMDB&tag=yes",
														"yes" ]
											});
								}, 1000);
					});
});
// ///////////////////////////////////
function queryResource(cpage, pageSize) {
	// 条件查询的参数
	var blur = $("#blur").val();
	var type = $("#type").val();
	var location = $("#location").val();
	var isReachable = $("#isReachable").val();
	var appNameInCmdb = $("#appNameInCmdb").val();
	var ownerInCmdb = $("#ownerInCmdb").val();
	var aRolePersonName = $("#aRolePersonName").val();
	// 查询所有资源，包括无效的
	var isOk = "0";
	var param = {
		"currentPage" : cpage,
		"pageSize" : pageSize,
		"blur" : blur,
		"type" : type,
		"location" : location,
		"isReachable" : isReachable,
		"appNameInCmdb" : appNameInCmdb,
		"ownerInCmdb" : ownerInCmdb,
		"aRolePersonName" : aRolePersonName,
		"isOk" : isOk
	};
	var paramUrl = 'resourcemanager!searchResourceList.action';
	pageAjax('pageDiv', 'wflistTemplate', 'tbodyList', 'queryResource',
			paramUrl, param, function() {
				// icheck
				$('input').iCheck({
					checkboxClass : 'icheckbox_square-red', // 注意square和blue的对应关系,用于type=checkbox
				});
			});

	// 全选、反选
	$('.checkedAll').on('ifChecked', function(event) {
		$('input').iCheck('check');
	});
	$('.checkedAll').on('ifUnchecked', function(event) {
		$('input').iCheck('uncheck');
	});
}
// ////////////////////////
function deleteResource(id) {
	layer.confirm('确定要删除吗？', {
		btn : [ '是', '否' ]
	// 按钮
	}, function(index) {
		$.post("resourcemanager!deleteResource.action", {
			"id" : id
		}, function(data) {
			if (data.result) {
				layer.close(index);
				$("tr[id='" + id + "']").remove();
			} else {
				layer.msg(data.message);
			}
		});
	}, function() {

	});
	return false;
}
// 编辑资源信息数据
function modifiedResource(id) {
	var index1 = layer.load(1, {
		shade : [ 0.3 ]
	// 0.1透明度的白色背景
	});
	// //
	setTimeout(function() {
		loadFrame("resourcemanager!modifyResource.action?id=" + id
				+ "&isEdit=0");
	}, 1000);
}

function viewResource(id) {
	var index1 = layer.load(1, {
		shade : [ 0.3 ]
	// 0.1透明度的白色背景
	});
	// //
	setTimeout(function() {
		loadFrame("resourcemanager!modifyResource.action?id=" + id
				+ "&viewflag=1&isEdit=1");
	}, 1000);
}
// batchcmdb页面的回掉函数
function f_batchcmdb() {
	queryResource(1, 10);
}