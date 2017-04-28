package cn.aplus.resp;

import java.io.Serializable;

public class PageRespObj implements Serializable {
	
	private static final long serialVersionUID = -2940983877096774934L;

	private int currentPage; // 当前页

	private long totalResults; // 总共记录数

	private int onePageSize; // 每页的数量
	
	private int totalPages;

	private boolean hasNextPage; // 下一页

	private boolean hasPreviousPage; // 上一页

	private boolean isFirstPage = false; // 是否为第一页
	
	private boolean isLastPage = false; // 是否为最后一页
	
	private String navigatePageNumbers ;

	public PageRespObj() {
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public int getOnePageSize() {
		return onePageSize;
	}

	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public String getNavigatePageNumbers() {
		return navigatePageNumbers;
	}

	public void setNavigatePageNumbers(String navigatePageNumbers) {
		this.navigatePageNumbers = navigatePageNumbers;
	}
	
	

}
