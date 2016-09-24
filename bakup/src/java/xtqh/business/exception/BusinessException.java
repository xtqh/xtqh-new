package xtqh.business.exception;

import xtqh.framework.base.exception.BaseException;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 3182422599699618026L;

	public BusinessException(Object Obj) {
		super(Obj.toString());
	}

	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
