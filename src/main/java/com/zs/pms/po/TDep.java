package com.zs.pms.po;

import java.io.Serializable;

public class TDep implements Serializable{

	/**
	 * 生成序列化标识
	 */
	private static final long serialVersionUID = 7269686623283977828L;

	private int id;
	private String name;
	private int pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Override
	public String toString() {
		return "TDep [id=" + id + ", name=" + name + ", pid=" + pid + "]";
	}
	
}
