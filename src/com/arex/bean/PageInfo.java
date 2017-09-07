package com.arex.bean;

import java.util.List;

public class PageInfo<T> {

	private long totalResults;
	private List<T> entityList;

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

}
