/**
 * 
 */
package xtqh.framework.base.paginate;

import java.util.List;

/**
 * 
 * <code>Pagination.java</code>
 * <p>
 * 
 * @function:分页对象类
 * 
 * @author liaoyingqiu
 * @date 2013-3-22 下午2:47:44
 * @version 1.0 </br>
 * @param <T>
 */
public class Pagination<T> {

	// 当前页
	private int sEcho;
	// 总条数
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	// 数据
	private List<T> records;
	private int pageSize = 10;
	private int totalCount;
	private int startIndex = 0;
	private int page;
	private int totalPages;

	public Pagination(List<T> records, int totalCount) {
		// 默认为10;
		// this.setPageSize(10);
		this.setStartIndex(0);
		this.setTotalCount(totalCount);
		this.setPage(1);
		this.setRecords(records);
		this.totalPages = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize != 0) {
			this.totalPages += 1;
		}
		this.setiTotalRecords(totalCount);

		this.setsEcho(1);
		this.setiTotalDisplayRecords(totalCount);

		this.subRecords(records, totalCount, pageSize, startIndex);

	}

	public Pagination(List<T> records, int totalCount, int startIndex) {
		// 默认为10
		// this.setPageSize(pageSize);
		this.setStartIndex(startIndex);
		this.setRecords(records);
		this.setTotalCount(totalCount);
		this.setPage(startIndex / this.pageSize + 1);
		this.totalPages = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize != 0) {
			this.totalPages += 1;
		}
		this.setiTotalRecords(totalCount);
		this.setiTotalDisplayRecords(totalCount);

		this.setsEcho(startIndex / this.pageSize + 1);
		this.subRecords(records, totalCount, pageSize, startIndex);
	}

	public Pagination(List<T> records, int totalCount, int pageSize,
			int startIndex) {
		this.setPageSize(pageSize);
		this.setStartIndex(startIndex);
		this.setRecords(records);
		this.setTotalCount(totalCount);
		this.setPage(startIndex / this.pageSize + 1);
		this.totalPages = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize != 0) {
			this.totalPages += 1;
		}
		this.setiTotalRecords(totalCount);
		this.setiTotalDisplayRecords(totalCount);

		this.setsEcho(startIndex / this.pageSize + 1);
		this.subRecords(records, totalCount, pageSize, startIndex);
	}

	private void subRecords(List<T> records, int totalCount, int pageSize,
			int startIndex) {
		if (null != records && !records.isEmpty()) {
			/**
			 * 说明已经是分页完成
			 */
			if (records.size() == totalCount) {
				if (records.size() > startIndex + pageSize) {
					this.setRecords(records.subList(startIndex, startIndex
							+ pageSize));
				} else if (records.size() > startIndex) {
					this.setRecords(records.subList(startIndex, records.size()));
				}
			}

		}

	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {

		this.records = records;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

}
