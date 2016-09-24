package xtqh.framework.log.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import xtqh.framework.log.OperateLogQueen;
import xtqh.framework.log.OperationLogService;
import xtqh.framework.log.dao.OperationLogDao;
import xtqh.framework.log.dao.entity.OperationLog;
import xtqh.framework.util.DateUtils;

@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService {
	private static Logger logger = Logger.getLogger(OperationLogServiceImpl.class);

	@Autowired
	public OperationLogDao operationLogDao;

	@Override
	public void insertLog() {
		ArrayList<OperationLog> logList = OperateLogQueen.getList();

		if (!CollectionUtils.isEmpty(logList)) {
			operationLogDao.batchInsert(logList);
		} else {
			logger.debug("当前队列中没有用户操作日志-" + DateUtils.Date2Str(new Date(), "yyyy-MM-dd HH:mm"));
		}
	}

}
