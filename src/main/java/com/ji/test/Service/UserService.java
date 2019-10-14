package com.ji.test.Service;

import java.util.Map;

import com.ji.test.Domain.UserVO;

public interface UserService {
	
	public UserVO login(Map<String,String> map);
	
	public int idCheck(String u_id);
	
	public int join(Map<String,String> map) throws Exception;
	
	public UserVO loginPw(String u_id);

}
