package com.ji.test.Service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ji.test.DAO.UserDAO;
import com.ji.test.Domain.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO uDao;

	@Override
	public UserVO login(Map<String, String> map) {
		
		return uDao.login(map);
	}

	@Override
	public int idCheck(String u_id) {
		// TODO Auto-generated method stub
		return uDao.idCheck(u_id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int join(Map<String, String> map) throws Exception {
		int res = uDao.join(map);
		if(res>0) {
			res = uDao.userAuthority(map.get("u_id"));
		}
		if(res==0) {
			throw new Exception();
		}
		
		return res;
	}

	@Override
	public UserVO loginPw(String u_id) {
		// TODO Auto-generated method stub
		return uDao.loginPw(u_id);
	}
	
	

}
