package com.ji.test.DAO;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDAOTest {
	@Inject
	private UserDAO userDao;
	
	private static Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	
	@Test
	public void testJoin() {
		Map<String,String> user = new HashMap<String,String>();
		user.put("u_id", "wlgud30");
		user.put("u_pw", "wlgud12");
		user.put("u_nm", "강지형");
		user.put("u_sex", "M");
		user.put("u_birth", "19941222");
		user.put("u_email", "wlgud30@naver.com");
		userDao.join(user);
	}
}
