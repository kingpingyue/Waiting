package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserSerivce;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;

@Service
@Transactional//需要开启事务的业务对象
public class UserServiceImpl implements UserSerivce {
	@Autowired
	UserDao dao;

	// 根据id获得权限列表
	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}
	// 根据原有权限整理菜单
	@Override
	public List<TPermission> getMenu(List<TPermission> pers) {
		// TODO Auto-generated method stub
		//创建容器
		List<TPermission>list=new ArrayList<>();
		//遍历权限列表
		for(TPermission per:pers) {
			//一级菜单
			if (per.getLev()==1) {
				//加载该一级菜单下的二级菜单
				//遍历
				for(TPermission per2:pers) {
					//二级权限的上级id等于一级权限的id
					if (per2.getPid()==per.getId()) {
						//添加子权限
						per.addChildren(per2);
					}
				}
				//加到新的集合中
				list.add(per);
			}
			
		}
		
		return list;
	}
//不知道 是什么查询
	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return dao.queryByCon(query);
	}
//批量删除
	@Override
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteByIds(ids);
	}
//修改
	@Override
	public void updateUser(TUser user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}
//新增
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) throws AppException{
		// TODO Auto-generated method stub
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "用户名不能使用admin");
		}
		
		MD5 md5=new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));
		//新增
		dao.insertUser(user);
		//返回主键
		return user.getId();
	}
	//通过id删除一条数据
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<TUser> queryByPage(int page, QueryUser query) {
		// TODO Auto-generated method stub
		/**
		 * 通过当前页计算起始数和截止数
		 */
		//起始数从1 开始
		int start=(page-1)*Constants.NUM+1;
		//截止数
		int end=page*Constants.NUM; 
		
		query.setStart(start);
		query.setEnd(end);
		
		return dao.queryByPage(query);
	}

	@Override
	public int queryPageCont(QueryUser query) {
		// TODO Auto-generated method stub
		//通过总条数计算总页数
		int cont =dao.queryCont(query);
		//判断能整除
		if (cont%Constants.NUM==0) {
			return cont/Constants.NUM;
		}else {
			return cont/Constants.NUM+1;
		}
		
	}
	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

}
