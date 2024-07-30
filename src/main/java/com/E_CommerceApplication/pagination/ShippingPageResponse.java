package com.E_CommerceApplication.pagination;

import java.util.List;

import com.E_CommerceApplication.payloads.ShippingDto;

public class ShippingPageResponse {
	private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastpage;
    private List<ShippingDto> content;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastpage() {
		return lastpage;
	}
	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}
	public List<ShippingDto> getContent() {
		return content;
	}
	public void setContent(List<ShippingDto> content) {
		this.content = content;
	}
    
    
}
