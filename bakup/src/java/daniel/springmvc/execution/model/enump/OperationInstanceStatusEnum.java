package daniel.springmvc.execution.model.enump;

/**
 * 
 * OpeartionInstanceStatus 实例步骤中操作状态，包括：<br>
 * NOT_STARTED: 未开始 <br>
 * QUEUED: 排队执行<br>
 * CANCELLED: 已取消<br>
 * EXECUTING: 执行中<br>
 * FINISHED: 执行结束<br>
 * 
 */
public enum OperationInstanceStatusEnum {
	NOT_STARTED, 
	QUEUED, 
	CANCELLED,
	EXECUTING,
	FINISHED;

//	private String propertyFilename = getClass().getSimpleName() + ".properties";
//    private static Properties properties;
//
//    private String value;
//
//    private void init() throws IOException{
//        if (properties == null) {
//            properties = new Properties();
//			properties.load(getClass().getResourceAsStream(propertyFilename));
//			value = (String) properties.get(this.toString());
//       }
//    }
//
//    public String getValue() throws IOException{
//        if (value == null) {
//            init();
//        }
//        return value;
//    }
	
}
