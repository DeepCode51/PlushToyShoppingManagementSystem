package com.soft.util;

import com.soft.common.domain.BaseDomain;


public class PaperUtil {
	/**
	 * 缺省页行数.
	 */
	private static final int DEFAULT_PAGE_LIMIT = 12;

	/**
	 * 起始行号.
	 */
	private int start = 0;
	
	/**
	 * 页码.
	 */
	private int pageNo = 1;

	/**
	 * 行数.
	 */
	private int limit = DEFAULT_PAGE_LIMIT;

	/**
	 * 返回总行数.
	 */
	private int totalCount = 0;

	/**
	 * 排序字段（例sp.spCode）.
	 */
	private String sort;

	/**
	 * 正序|反序（例ASC）.
	 */
	private String dir;
	
	
	   /**
     * 设置分页属性.
     * 
     * @param domain
     *            输入DO对象
     */
	public void setPagination(BaseDomain domain) {
        // 设置分页属性: start,limit,sort,order
        domain.setStart((getPageNo() - 1) * getLimit());
        domain.setLimit(getLimit());
        domain.setSort(getSort());
        domain.setOrder(getDir());
    }
    
    public int getPageCount() {
    	if (limit!=0) {
    		return (totalCount+limit-1)/limit;
		}
		return totalCount;
	}
    
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
	}

	public int getStart() {
		return start;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String getSort() {
		return sort;
	}

	public String getDir() {
		return dir;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
