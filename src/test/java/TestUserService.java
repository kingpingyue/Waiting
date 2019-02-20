import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserSerivce;
import com.zs.pms.vo.QueryUser;

import oracle.sql.DATE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {
	@Autowired
	UserSerivce us;
	

	public void Testrole() {
		List<TPermission> list1 = us.queryByUid(3084);
		
		for (TPermission per:list1) {
			System.out.println(per.getPname());
		}
		System.err.println("------------整理后的-------------------");
		for (TPermission per1:us.getMenu(list1)) {
			//一级权限
			System.out.println(per1.getPname());
			for (TPermission per2:per1.getChildren()) {
				System.out.println("----"+per2.getPname());
			}
		}
	}
	

	public void TestQuery() {
		//创建查询对象
		QueryUser query=new QueryUser();
		query.setSex("男"); 
		for(TUser user:us.queryByPage(1, query)) {
			System.err.println(user.getId()+"'、"+user.getLoginname());
		}
		System.err.println("共"+us.queryPageCont(query)+"页");
	}
	
	public void TestDeletes() {
		int []ids= {1000,1001};
		us.deleteByIds(ids);
	}
	@Test
	public void TestIsert() {
		TUser user=new TUser();
		TDep dep=new TDep();
		dep.setId(2);
		user.setDept(dep);
		user.setSex("男");
		user.setEmail("insert@qq.com");
		user.setLoginname("insert12");
		user.setPassword("123");
		user.setRealname("新增数据10");
		user.setPic("insert.jpg");
		user.setIsenabled(1);
		user.setCreator(1000);
		user.setBirthday(new Date());
		try {
			System.out.println(us.insertUser(user));
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void TestUpdate() {
		TUser user=new TUser();
		user.setId(1000);
		//TDep dep=new TDep();
		//dep.setId(6);
		//user.setDept(dep);
//		user.setSex("男");
//		user.setEmail("insert@qq.com");
//		user.setLoginname("insert1");
//		user.setPassword("123");
//		user.setRealname("新增数据");
//		user.setPic("insert.jpg");
		user.setIsenabled(-1);
//		user.setCreator(1000);
//		user.setBirthday(new Date());
		us.updateUser(user);
	}
	
	
	public void TestDeletById() {
		int id =1002;
		us.deleteById(id);
	}
	
}
