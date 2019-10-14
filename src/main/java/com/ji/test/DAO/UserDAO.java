package com.ji.test.DAO;

import java.util.Map;

import com.ji.test.Domain.UserVO;

public interface UserDAO {
	public String getTime();
	
	public int join(Map<String,String> map);
	
	public UserVO login(Map<String,String> map);
	
	public int idCheck(String u_id);
	
	public UserVO loginPw(String u_id);
	
	public int userAuthority(String u_id);
}
