package daniel.springmvc.execution.model.enump;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * ProcessInstanceStatus 实例任务状态，包括：<br>
 * <ul>
 * <li>NOT_STARTED: 未开始 </li>
 * <li>PARTIALLY_PAUSED: 部分暂停</li>
 * <li>PAUSED: 已暂停 </li>
 * <li>PARTIALLY_CANCELLED: 部分取消</li>
 * <li>CANCELLED: 已取消</li>
 * <li>EXECUTING: 执行中</li>
 * <li>FINISHED: 已完成</li>
 * </ul>
 */
public enum ProcessInstanceStatusEnum {
	NOT_STARTED, 
	PARTIALLY_PAUSED,
	PAUSED,
	PARTIALLY_CANCELLED,
	CANCELLED,
	EXECUTING,
	FINISHED;
    
	private String propertyFilename = getClass().getSimpleName() + ".properties";
    private static Properties properties;

    private String value;

    private void init() throws IOException{
        if (properties == null) {
            properties = new Properties();
			properties.load(getClass().getResourceAsStream(propertyFilename));
			value = (String) properties.get(this.toString());
       }
    }

    public String getValue() throws IOException{
        if (value == null) {
            init();
        }
        return value;
    }

}
