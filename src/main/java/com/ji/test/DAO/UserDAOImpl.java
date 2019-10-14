package com.ji.test.DAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ji.test.Domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	private static final String namespace = "userMapper.";

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int join(Map<String,String> map) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert(namespace+"userJoin", map);
	}

	@Override
	public UserVO login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"login", map);
	}

	@Override
	public int idCheck(String u_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"idCheck", u_id);
	}

	@Override
	public UserVO loginPw(String u_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"loginPw", u_id);
	}

	@Override
	public int userAuthority(String u_id) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+"userAuthority", u_id);
		
	}
	
	

}
