package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 权限表
 * @author Administrator
 *
 */
public class TPermission implements Serializable {

	/**
	 * 生成序列化标识
	 */
	private static final long serialVersionUID = -4744636704305004317L;
//id
	private int id;
	//权限名
	private String pname;
	//上级
	private int pid;
	//等级
	private int lev;
	//叶子
	private int isleaf;
	//顺序
	private int sort;
	//路径
	private String url;
	//图片
	private String iocn;
	//子权限集
	private List<TPermission>children=new ArrayList<>();
	//添加子权限
	public void addChildren(TPermission per) {
		children.add(per);
		
	}
	
	public List<TPermission> getChildren() {
		return children;
	}
	public void setChildren(List<TPermission> children) {
		this.children = children;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIocn() {
		return iocn;
	}
	public void setIocn(String iocn) {
		this.iocn = iocn;
	}
	@Override
	public String toString() {
		return "TPermission [id=" + id + ", pname=" + pname + ", pid=" + pid + ", lev=" + lev + ", isleaf=" + isleaf
				+ ", sort=" + sort + ", url=" + url + ", iocn=" + iocn + "]";
	}
	
}
