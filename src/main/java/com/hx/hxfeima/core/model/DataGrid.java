package com.hx.hxfeima.core.model;

import java.util.List;

public class DataGrid {

	private List rows;

	private long total = 0;

	public DataGrid() {

	}

	public DataGrid(List rows, long total) {
		this.rows = rows;
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
