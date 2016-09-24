package xtqh.framework.log;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import xtqh.framework.log.dao.entity.OperationLog;


public class OperateLogQueen {
	private static Logger logger = Logger.getLogger(OperateLogQueen.class);

	// 最大队列大小
	private final static int MAX_SIZE = 20000;
	private static LinkedBlockingQueue<OperationLog> list = new LinkedBlockingQueue<OperationLog>(MAX_SIZE);

	/**
	 * 生成用户操作日志
	 * 
	 * @param notify
	 */
	public static void produceOperationLog(OperationLog operLog) {
		if (list.size() == MAX_SIZE) {
			logger.warn("暂时无法加入新的通知,仓库已满,请系统管理员查看满仓原因!");
		} else {
			try {
				list.put(operLog);
				logger.info("用户操作日志队列入队一条数据，队列目前大小为:" + list.size());
			} catch (InterruptedException e) {
				logger.error("录通知入队列时出错", e);
			}
		}
	}

	/**
	 * 消费队列
	 * 
	 * @return
	 */
	public static OperationLog consumeOperationLog() {
		OperationLog operLog = null;
		try {
			operLog = list.take();
			logger.debug("用户操作日志队列已消费一条，当前队列大小为：" + list.size());
		} catch (InterruptedException e) {
			logger.error("从队列取通知时出错", e);
		}
		return operLog;
	}

	public static ArrayList<OperationLog> getList() {
		ArrayList<OperationLog> logList = new ArrayList<OperationLog>();
		if (!list.isEmpty() && list.size() > 0) {
			logList.add(OperateLogQueen.consumeOperationLog());
		}
		return logList;
	}
}
