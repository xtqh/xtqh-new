package xtqh.framework.log.dao;

import xtqh.framework.base.orm.PersistService;
import xtqh.framework.log.dao.entity.OperationLog;

public interface OperationLogDao extends PersistService{
	
	/**
	 * 插入一条用户操作日志记录
	 * @param operLog
	 */
	public void insertLog(OperationLog operLog);
}
