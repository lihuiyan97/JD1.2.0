package com.vo;

public class Category2 {

	private int cid;
	private String cname2;
	private String cdesc2;
	private int fid;

	private Category category; 
	

	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname2() {
		return cname2;
	}

	public void setCname2(String cname2) {
		this.cname2 = cname2;
	}

	public String getCdesc2() {
		return cdesc2;
	}

	public void setCdesc2(String cdesc2) {
		this.cdesc2 = cdesc2;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Category2 [cid=" + cid + ", cname2=" + cname2 + ", cdesc2="
				+ cdesc2 + ", fid=" + fid + "]";
	}

}
