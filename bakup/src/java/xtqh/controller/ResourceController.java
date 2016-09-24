package xtqh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xtqh.business.BResourceService;
import xtqh.business.bean.BResource;
import xtqh.business.exception.BusinessException;
import xtqh.framework.base.BaseController;
import xtqh.framework.base.paginate.Pagination;

@Controller
@RequestMapping("/resourceController")
public class ResourceController extends BaseController {

	@Autowired
	private BResourceService bResourceService;

	@RequestMapping(value = "/index")
	public ModelAndView fetchResourceList(Model model, ModelAndView modelAndView) {

		
		modelAndView.setViewName("forward:/pages/resource/resourceList.jsp");

		return modelAndView;
	}

	@RequestMapping("/resourceList")
	@ResponseBody
	public Pagination<BResource> resourceList(Model model, ModelAndView modelAndView) {
		Pagination<BResource> resourceList = null;
		try {
			String searchKey = this.getParameterFromRequest("searchKey");
			/**
			 * 获取请求参数，并封裝
			 */
			Map<String, String> parMap = new HashMap<String, String>();
			parMap.put("searchKey", searchKey);
			/**
			 * 查询对应的resourceList
			 */
			resourceList = bResourceService.getResourceList(parMap, this.getPaginationParameter());

//			String a = null;
//			a.length();

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.handleException(e);
//			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.handleException(e);
			return null;
		}
		return resourceList;
	}

	@RequestMapping(value = "/resourceDetail")
	public ModelAndView resourceDetail(Model model, ModelAndView modelAndView) {

		
		modelAndView.setViewName("forward:/pages/resource/resourceDetail.jsp");

		return modelAndView;
	}
}
