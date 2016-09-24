package xtqh.framework.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import xtqh.framework.base.paginate.PaginationParameter;


public abstract class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 异常处理，参数标准后
	 */

	/**
	 * get parameter from request and decode
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	protected String getParameterFromRequest(String key) {
		String parValue = null;
		if (null != key && !"".equals(key)) {
			parValue = request.getParameter(key);
			if (null != parValue && !"".equals(parValue.trim())) {
				try {
					parValue = URLDecoder.decode(parValue, "UTF-8").trim();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return null;
			}
		}
		return parValue;
	}

	/**
	 * 异常处理
	 */
	protected void handleException(Exception excetipn) {
		/**
		 * 异常信息，打印到页面
		 */
		try {
			String requestType = request.getHeader("X-Requested-With");
			System.out.println("#######################  " + requestType + "  #######################");
			request.setAttribute("errorMsg", excetipn.toString());
			String path = request.getContextPath();
			response.getWriter().write("<script type='text/JavaScript'>window.open('" + path
					+ "/pages/common/error.jsp?errorMsg=" + excetipn.toString() + "','_blank'); </script>");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 分页参数
	 * 
	 * @return
	 */
	protected PaginationParameter getPaginationParameter() {

		String pageStr = this.getParameterFromRequest("page");
		String rowsStr = this.getParameterFromRequest("rows");

		String sidx = this.getParameterFromRequest("sidx");
		String sord = this.getParameterFromRequest("sord");

		PaginationParameter parameter = this.getPagination(pageStr, rowsStr);
		parameter.setSidx(sidx);
		parameter.setSord(sord);

		return parameter;
	}

	private PaginationParameter getPagination(String pageStr, String rowsStr) {
		/**
		 * page和rows只可能是整数
		 */
		int page = 0;
		int rows = 0;
		if (null != pageStr && pageStr.matches("\\d+")) {
			page = Integer.valueOf(pageStr);
		}
		if (null != rowsStr && rowsStr.matches("\\d+")) {
			rows = Integer.valueOf(rowsStr);
		}
		PaginationParameter parameter = new PaginationParameter(page, rows);
		return parameter;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	@Resource
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Resource
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
