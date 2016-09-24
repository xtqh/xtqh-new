/**
 * 
 */
package xtqh.framework.base.paginate;

/**
 * @author liaoyingqiu
 * 
 */
public class PaginationParameter {
	private int start;
	private int pageSize = 10;
	private int page;

	private String sidx;
	private String sord;

	public PaginationParameter() {
		this.start = 0;
	}

	public PaginationParameter(int currentPage, int pageSize) {
		this.setPage(currentPage);
		this.pageSize = pageSize;
		this.start = (this.page - 1) * this.pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			this.page = 1;
		}
		this.page = page;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
