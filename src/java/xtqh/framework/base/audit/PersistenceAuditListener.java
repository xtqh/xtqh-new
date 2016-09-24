package xtqh.framework.base.audit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xtqh.dao.entity.User;
import xtqh.framework.base.BaseEntity;
import xtqh.framework.common.Constants;
import xtqh.framework.log.OperateLogQueen;
import xtqh.framework.log.dao.entity.OperationLog;

public class PersistenceAuditListener {

	Logger logger = Logger.getLogger(PersistenceAuditListener.class);

	@PreUpdate
	public <T> void setUserForUpdate(BaseEntity auditEntity) {
		// 统一从session中获取
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
		if (user != null)
			auditEntity.setUpdator(user.getUserId());
		else {
			/**
			 * 这为了维护期同步时，CREATOR 为CAMA
			 */
			if (auditEntity.getUpdator() != null && "CAMA".equals(auditEntity.getUpdator())) {

			} else {
				// 无法获取用户信息
				auditEntity.setUpdator("");
			}
		}
		// 更新时间为最新事件
		auditEntity.setUpdateTime(new Date());
	}

	@PrePersist
	public <T> void setUserForInsert(BaseEntity auditEntity) {

		try {
			User user = null;
			if (null != RequestContextHolder.getRequestAttributes()) {
				// 统一从session中获取
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();

				user = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
			}

			if (user != null)
				auditEntity.setCreator(user.getUserId());
			else {
				/**
				 * 这为了维护期同步时，CREATOR 为CAMA
				 */
				if (auditEntity.getCreator() != null && "CAMA".equals(auditEntity.getCreator())) {

				} else {
					// 无法获取用户
					auditEntity.setCreator("itmonitor");
				}
			}

			auditEntity.setCreateTime(new Date());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@PostUpdate
	public <T> void updateOperationLog(T entity) {
		OperationLog operLog = this.getTableNameByEntity(entity, "update", entity.toString());
		OperateLogQueen.produceOperationLog(operLog);
	}

	@PostPersist
	public <T> void insertOperationLog(T entity) {
		OperationLog operLog = this.getTableNameByEntity(entity, "insert", entity.toString());
		OperateLogQueen.produceOperationLog(operLog);
	}

	public <T> OperationLog getTableNameByEntity(T entity, String operation, String content) {
		Table table = entity.getClass().getAnnotation(Table.class);
		String tableName = table.name();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = request.getRemoteAddr();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);

		user = user == null ? new User() : user;

		String uuid = UUID.randomUUID().toString();

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("logModule.properties");
		Properties p = new Properties();
		String module = "";
		try {
			p.load(in);
			module = p.getProperty(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		OperationLog operLog = new OperationLog();
		operLog.setId(uuid);
		operLog.setLoginIp(ip);
		operLog.setModule(module);

		operLog.setOperation(operation);
		operLog.setOperationContent(content);
		operLog.setTableName(tableName);
		operLog.setUserId(user.getUserId());
		operLog.setOperationTime(new Date());

		/*
		 * System.out.println("*****************************");
		 * System.out.println(uuid); System.out.println(tableName);
		 * System.out.println(ip); System.out.println(user.getUserId());
		 * System.out.println(operation); System.out.println(new Date());
		 * System.out.println(content); System.out.println(module);
		 * System.out.println("******************************");
		 */
		return operLog;
	}

	/*
	 * public <T> String getAnnotationIdName(T entity){ String fieldName = null;
	 * Field[] fields = entity.getClass().getDeclaredFields(); out:for (int i =
	 * 0; i < fields.length; i++) { Annotation[] as =
	 * fields[i].getAnnotations(); for (Annotation a : as) { if (a instanceof
	 * Id){ fieldName = fields[i].getName(); break out; } } } return fieldName;
	 * }
	 * 
	 * public <T> void getbeforeUpdateContent(T entity){ String id =
	 * this.getAnnotationIdName(entity); T tt = null; try { tt =
	 * (T)persistService.find(entity.getClass(),
	 * PropertyUtils.getProperty(entity, id)); } catch (IllegalAccessException
	 * e) { e.printStackTrace(); } catch (InvocationTargetException e) {
	 * e.printStackTrace(); } catch (NoSuchMethodException e) {
	 * e.printStackTrace(); }
	 * 
	 * StringBuilder content = new StringBuilder();
	 * 
	 * Field[] fields = tt.getClass().getDeclaredFields(); String value = null;
	 * String name = null; for (int i = 0; i < fields.length; i++) { name =
	 * fields[i].getName(); try { value = (String) PropertyUtils.getProperty(tt,
	 * name); } catch (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (InvocationTargetException e) { e.printStackTrace(); } catch
	 * (NoSuchMethodException e) { e.printStackTrace(); }
	 * content.append(name).append("=").append(value).append(","); }
	 * content.setLength(content.length()-1); System.out.println(content); }
	 */

}
