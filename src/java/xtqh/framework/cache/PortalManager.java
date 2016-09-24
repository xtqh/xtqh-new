package xtqh.framework.cache;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Yan Fugen
 *
 */

@Service("PortalManager")
public class PortalManager {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpSession session;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

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

}
