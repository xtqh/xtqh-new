package xtqh.business.bean;

/**
 * 
 * @author Yan Fugen
 *
 */

public class BResource {

	private String resourceId;

	private String name;

	private String resourceType;

	private String hostname;

	private String applicationNames;

	private String applicationIDs;

	// private List<Application> applicatoinList;

	private String controllingIp;

	private String resourceIp;

	private String location;

	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getControllingIp() {
		return controllingIp;
	}

	public void setControllingIp(String controllingIp) {
		this.controllingIp = controllingIp;
	}

	public String getResourceIp() {
		return resourceIp;
	}

	public void setResourceIp(String resourceIp) {
		this.resourceIp = resourceIp;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getApplicationNames() {
		return applicationNames;
	}

	public void setApplicationNames(String applicationNames) {
		this.applicationNames = applicationNames;
	}

	public String getApplicationIDs() {
		return applicationIDs;
	}

	public void setApplicationIDs(String applicationIDs) {
		this.applicationIDs = applicationIDs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
