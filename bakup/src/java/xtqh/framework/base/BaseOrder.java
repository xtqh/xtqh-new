package xtqh.framework.base;

/**
 * 
 * @author Yan Fugen
 *
 */
public abstract class BaseOrder {

	private String orderColumn;

	private boolean isAsc;

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

}
