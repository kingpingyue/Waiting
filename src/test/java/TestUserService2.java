import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserSerivce;
import com.zs.pms.service.UserService2;
import com.zs.pms.vo.QueryUser;

import oracle.sql.DATE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService2 {
	@Autowired
	UserService2 us;
	@Test
	public void TestQuery() {
		QueryUser query=new QueryUser();
		query.setSex("男");
		System.err.println(us.queryByCon(query).size());
	}

}
