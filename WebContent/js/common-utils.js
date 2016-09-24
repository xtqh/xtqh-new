/* 
 * div: 自定义DIV ID,把本次ajax请求的相关html包含在里面，用于遮罩层
 * async: false 同步； true 异步
*/
function slAjax(div, url, param, successFun, async){
	var pDiv= $("#"+div);
	pDiv.css('position','relative');
	if( pDiv.attr("style").indexOf("width")==-1) //给默认宽度100%，否则IE显示异常
		pDiv.css('width', '100%');
	if($("#menuBar_"+div).length==0){
		pDiv.attr('name','1');
		pDiv.append('<div id="menuBar_'+ div +'" class="progressBar" style="display: none;">处理中，请稍等...</div>');
		pDiv.append('<div id="menuGround_'+ div +'" class="background" style="display: none;"></div> ');
	}
	$("#menuGround_"+div).height( pDiv.height() );
	$("#menuGround_"+div+",#menuBar_"+div).show();
	pDiv.attr('name', parseInt(pDiv.attr('name'))+1 ); //通过name来控制类似update再load的类型
	$.ajax({
		type:"POST",
		async:async,
		dataType:'json',
		url:url,
		data:param,
		success:successFun, //TD 错误
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$.ligerDialog.error("系统内部异常" +
					  "</br>status:" + XMLHttpRequest.status +
					  "</br>textStatus:" + textStatus +
					  "</br>message:"+ errorThrown.message );
		},
		complete:function(){
			pDiv.attr('name', parseInt(pDiv.attr('name'))-1 );
			if( pDiv.attr('name')==1 )
				$("#menuGround_"+div+",#menuBar_"+div).hide(); 
		}
	});
}
function slAjax1(div, url, param, successFun, async){
	var pDiv= $("#"+div);
	pDiv.css('position','relative');
	if( pDiv.attr("style").indexOf("width")==-1) //给默认宽度100%，否则IE显示异常
		pDiv.css('width', '100%');
	if($("#menuBar_"+div).length==0){
		pDiv.attr('name','1');
		pDiv.append('<div id="menuGround_'+ div +'" class="background" style="display: none;"></div> ');
	}
	$("#menuGround_"+div).height( pDiv.height() );
	pDiv.attr('name', parseInt(pDiv.attr('name'))+1 ); //通过name来控制类似update再load的类型
	$.ajax({
		type:"POST",
		async:true,
		dataType:'json',
		url:url,
		data:param,
		success:successFun, //TD 错误
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$.ligerDialog.error("系统内部异常" +
					  "</br>status:" + XMLHttpRequest.status +
					  "</br>textStatus:" + textStatus +
					  "</br>message:"+ errorThrown.message );
		},
		complete:function(){
			pDiv.attr('name', parseInt(pDiv.attr('name'))-1 );
			if( pDiv.attr('name')==1 )
				$("#menuGround_"+div+",#menuBar_"+div).hide(); 
		}
	});
}
function pageAjax(div, jsrenderId, htmlTargetId, thisMethod, url, param ,onComplete){
		slAjax(div, url, param, function(data){
		if( data.list==''){
			$( "#"+htmlTargetId ).html('<tr><td colspan=100 style="color: red; font-weight: bolder;">无数据</td></tr>');
		} else {
			$( "#"+htmlTargetId ).html(
				$( "#"+jsrenderId ).render( data.list )
			);
			var pageId = div+"page";
			$("#"+div).append('<div id="'+pageId+'"></div>');
			addPageTool(pageId, data, thisMethod);
		}
		if(onComplete)
		onComplete(data);
	}, true);
}
function pageAjax1(div, jsrenderId, htmlTargetId, thisMethod, url, param,onComplete ){

	slAjax1(div, url, param, function(data){
		if( data.list==''){
			$( "#"+htmlTargetId ).html('<tr><td colspan=100 style="color: red; font-weight: bolder;">无数据</td></tr>');
		} else {
			$( "#"+htmlTargetId ).html(
				$( "#"+jsrenderId ).render( data.list )
			);
		}
		if(onComplete)
			onComplete(data);
	}, true);
}
/*通过查询不进行分页*/
function pageAjaxall(div, jsrenderId, htmlTargetId, thisMethod, url, param ){
	
	slAjax(div, url, param, function(data){
		if( data.length==''){
			$( "#"+htmlTargetId ).html('<tr><td colspan=100 style="color: red; font-weight: bolder;">无数据</td></tr>');
		} else {
			$( "#"+htmlTargetId ).html(
					$( "#"+jsrenderId ).render( data )
			);
		}
		
	}, true);
}
/**
 * 添加分页工具条
 */
function addPageTool(cid,pager,searchMethod){
	var container = $('#'+cid);
	var str = "<div class='pages l'>";
	str+='<a href="javascript:void(0)"  onclick="'+searchMethod+'('+1+','+pager.pageSize+')" title="第一页">&laquo;&laquo;</a> '; 
	str+='<a href="javascript:void(0)"  onclick="'+searchMethod+'('+pager.previousPage+','+pager.pageSize+')" title="上一页">&laquo;</a> ';
	str+='<a href="javascript:void(0)" >第 '+pager.cpage+' 页  / 共 '+pager.totalPage+' 页</a></li>';
	str+='<a href="javascript:void(0)"  onclick="'+searchMethod+'('+pager.nextPage+','+pager.pageSize+')" title="下一页">&raquo;</a> '; 
	str+='<a href="javascript:void(0)"  onclick="'+searchMethod+'('+pager.totalPage+','+pager.pageSize+')" title="最后页">&raquo;&raquo;</a>';
	str+='<a href="javascript:void(0)" >匹配记录 '+pager.totalItem+'条 </a></li>';
	str+='</div>';
	str += "<input type='hidden' id='cpage' value='"+pager.cpage+"' />"
	container.html(str);
	try 
	{ 
		$('#pagecount').val(pager.cpage);
	} 
	catch (e) 
	{ 
	} 
}

/*
 * 自动加载数据到form
 */
function slAutoLoad(formId, data){
	var inputArray= $("#guestForm").find("input");
	for(var i=0; i<inputArray.length; i++){
		var id= $("#guestForm").find("input")[i].id;
		if(data[id]!=undefined)
			$("#"+id).val(data[id]);
	}
}

function slSetCanNes(formId, canArray, necArray){
	canArray= slUnique(canArray);
	necArray= slUnique(necArray);
	$("#"+formId).find("input").attr("disabled","disabled");
	$("#"+formId).find("input").attr("class","input_dis");
	$("#"+formId).find("select").attr("disabled","disabled");
	$("#"+formId).find("select").attr("class","input_dis");
	//TD 根据name不够通用，临时使用
	$("input[name='customerType']").removeAttr("disabled");
	$("font[name='necInput']").remove();
	$("#"+formId).find("input").removeAttr("nec");
	$("#"+formId).find("span").removeAttr("nec");
	$("#"+formId).find("input[type='hidden']").removeAttr("disabled");
	for(var i=0; i<canArray.length; i++){
		$("#"+canArray[i]).removeAttr("disabled");
		$("#"+canArray[i]).attr("class","input");
	}
	for(var i=0; i<necArray.length; i++){
		$("#"+necArray[i]).attr('nec','true');
		var obj= $("#"+necArray[i]).parent().find("font[name='necInput']");
		if(obj.length==0)
			$("#"+necArray[i]).parent().append("<font name='necInput' color='red'>*</font>");
	}
}


function slUnique(data){ 
	data = data || []; 
	var a = {}; 
	len = data.length; 
	for (var i=0; i<len;i++){ 
		var v = data[i]; 
		if (typeof(a[v]) == 'undefined'){ 
			a[v] = 1; 
		} 
	}; 
	data.length=0; 
	for (var i in a){ 
		data[data.length] = i; 
	} 
	return data; 
} 

/*
 * form表单数据转json格式
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/**
 *   数据字典
 * @param type
 * @param selectid
 * @return
 */

function dataDic(type,selectid){
	$("#"+selectid).children().remove();
	$.ajax({
		type:"post",
		url:"../dataDic!getDataDicValueList.action",
		async:true,
		data:{"type":type},
		dataType:"json",
		success:function(data){
			jQuery.each(data,function(i,item){
				$("#"+selectid).append("<option value = '"+item.CODE+"'>"+item.NAME+"</option>");
			});
		}
	});
	
}
//浮点数加法运算
function FloatAdd(arg1,arg2){  
 var r1, r2, m, c;
        try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
        try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
        c = Math.abs(r1 - r2);
        m = Math.pow(10, Math.max(r1, r2))
        if (c > 0) {
            var cm = Math.pow(10, c);
            if (r1 > r2) {
                arg1 = Number(arg1.toString().replace(".", ""));
                arg2 = Number(arg2.toString().replace(".", "")) * cm;
            }
            else {
                arg1 = Number(arg1.toString().replace(".", "")) * cm;
                arg2 = Number(arg2.toString().replace(".", ""));
            }
        }
        else {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", ""));
        }
        return (arg1 + arg2) / m

}






//浮点数减法运算  
 function FloatSub(arg1,arg2){  
 var r1,r2,m,n;  
 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
 m=Math.pow(10,Math.max(r1,r2));  
 //动态控制精度长度  
 n=(r1>=r2)?r1:r2;  
 return ((arg1*m-arg2*m)/m).toFixed(n);  
 }  
   
 //浮点数乘法运算  
 function FloatMul(arg1,arg2)   
 {   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
  }   
  
  
//浮点数除法运算  
function FloatDiv(arg1,arg2){   
	var t1=0,t2=0,r1,r2;   
	try{t1=arg1.toString().split(".")[1].length}catch(e){}   
	try{t2=arg2.toString().split(".")[1].length}catch(e){}   
	with(Math){   
		r1=Number(arg1.toString().replace(".",""))   
		r2=Number(arg2.toString().replace(".",""))   
		return (r1/r2)*pow(10,t2-t1);   
	}   
}
  
  
/**
 * 
 * @param {} type
 * @param {} selectid
 */
function projectList(selectid,checkedValue){
	$("#"+selectid).empty();
	$.ajax({
		type:"post",
		url:"../OA/oabaoxiao!loadAllProject.action",
		async:true,
		dataType:"json",
		success:function(data){
			$("#"+selectid).append("<option value = '"+0+"'>--------请选择------</option>");
			jQuery.each(data,function(i,item){
				if (checkedValue==item.id){
					$("#"+selectid).append("<option value = '"+item.id+"' selected='selected'>"+item.name+"</option>");

				}else{
					$("#"+selectid).append("<option value = '"+item.id+"'>"+item.name+"</option>");

				}
			});
		}
	});
	
}


function appNameLists(selectid,checkedValue,flag){
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadUniqueAppNameList.action",
		data:{"flag":flag},
		async:false,
		dataType:"json",
		success:function(data){
			$("#"+selectid).autocomplete(data.list , {
				minChars:0,
				width:260,
				max:999,
				dataType:"json",
				matchContains: true,
				formatItem: function(row, i, max) {
					return row.APP_NAME;
				},
				formatMatch: function(row, i, max) {
					return row.APP_NAME;
				},
				formatResult: function(row) {
					return row.APP_NAME;
				}
			});
		}
	});
}

function milestoneLists(selectid,checkedValue,flag){
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadMilestoneNameList.action",
		data:{"flag":flag},
		async:false,
		dataType:"json",
		success:function(data){
			$("#"+selectid).autocomplete(data.list , {
				minChars:0,
				width:260,
				max:999,
				dataType:"json",
				matchContains: true,
				formatItem: function(row, i, max) {
					return row.MILESTONE;
				},
				formatMatch: function(row, i, max) {
					return row.MILESTONE;
				},
				formatResult: function(row) {
					return row.MILESTONE;
				}
			});
		}
	});
}
/**
 * 
 * @param {} type
 * @param {} selectid
 */
function appNameList(selectid,checkedValue){
	$("#"+selectid).empty();
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadUniqueAppNameList.action",
		async:false,
		dataType:"json",
		success:function(data){
			$("#"+selectid).append("<option value = '-1'>-----请选择----</option>");
			jQuery.each(data.list,function(i,item){
				if (checkedValue==item.APP_NAME){
					$("#"+selectid).append("<option value = '"+item.APP_NAME+"' selected='selected'>"+item.APP_NAME+"</option>");
				}else{
					$("#"+selectid).append("<option value = '"+item.APP_NAME+"'>"+item.APP_NAME+"</option>");
				}
			});
		}
	});
}

function appNameList1(selectid){
	$.ajax({
		type:"post",
		url:"./commonUtil/common!loadUniqueAppNameList.action",
		async:false,
		dataType:"json",
		success:function(data){
			$("#"+selectid).autocomplete(data.list , {
				minChars:0,
				width:260,
				max:999,
				dataType:"json",
				matchContains: true,
				formatItem: function(row, i, max) {
					return row.APP_NAME;
				},
				formatMatch: function(row, i, max) {
					return row.APP_NAME;
				},
				formatResult: function(row) {
					return row.APP_NAME;
				}
			}).result(function(event,row,formatted){
				queryMilstone();
			});
		}
	});
}


function milestoneList(selectid,checkedValue){
	$("#"+selectid).empty();
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadMilestoneNameList.action",
		async:false,
		dataType:"json",
		success:function(data){
			$("#"+selectid).append("<option value = '-1'>--------请选择------</option>");
			jQuery.each(data.list,function(i,item){
				if (checkedValue==item.MILESTONE){
					$("#"+selectid).append("<option value = '"+item.MILESTONE+"' selected='selected'>"+item.MILESTONE+"</option>");
				}else{
					$("#"+selectid).append("<option value = '"+item.MILESTONE+"'>"+item.MILESTONE+"</option>");
					
				}
			});
		}
	});
}

function wfNameList(selectid,checkedValue,flag){
	$("#"+selectid).empty();
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadwfNameList.action",
		data:{"flag":flag},
		async:true,
		dataType:"json",
		success:function(data){
			$("#"+selectid).append("<option value = '-1'>--------请选择------</option>");
			jQuery.each(data.list,function(i,item){
				if (checkedValue==item.id){
					$("#"+selectid).append("<option value = '"+item.id+"' selected='selected'>"+item.wf_name+"</option>");

				}else{
					$("#"+selectid).append("<option value = '"+item.id+"'>"+item.wf_name+"</option>");

				}
			});
		}
	});
}
function wfNameInstList(selectid,checkedValue,flag){
	$("#"+selectid).empty();
	$.ajax({
		type:"post",
		url:"../commonUtil/common!loadwfNameInstList.action",
		data:{"flag":flag},
		async:true,
		dataType:"json",
		success:function(data){
			$("#"+selectid).append("<option value = '-1'>--------请选择------</option>");
			jQuery.each(data.list,function(i,item){
				if (checkedValue==item.id){
					$("#"+selectid).append("<option value = '"+item.id+"' selected='selected'>"+item.wf_inst_sn+"</option>");

				}else{
					$("#"+selectid).append("<option value = '"+item.id+"'>"+item.wf_inst_sn+"</option>");

				}
			});
		}
	});
}

//表格展现编辑信息
function jrShow(row){
		var str = "<div><span>查看</span><span>编辑</span><span>删除</span></div>"
		$("#"+row.id).append(str);
		
}

function jrClear(row){
		$("#"+row.id).remove();
	
}


function loadFrame(url){
	parent.loadScr(url);
}

