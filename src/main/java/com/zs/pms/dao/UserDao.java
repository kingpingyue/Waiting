package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {
	//根据用户id获得全限
	public List<TPermission> queryByUid(int id) ;
	//根据条件查询
	public List<TUser>queryByCon(QueryUser query);
	//批量删除
	public void deleteByIds(int []ids);
	//修改
	public void updateUser(TUser user);
	//新增
	public int insertUser(TUser user);
	//删除
	public void deleteById(int id);
	//分页查询
	public List<TUser>queryByPage(QueryUser query);
	//获得总页数
	public int queryCont(QueryUser query);
	//根据id(主键)获取
	public TUser queryById(int id);
}
