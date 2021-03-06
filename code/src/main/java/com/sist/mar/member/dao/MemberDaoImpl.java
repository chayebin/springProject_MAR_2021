package com.sist.mar.member.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mar.cmn.DTO;
import com.sist.mar.member.domain.MemberVO;

@Repository
public class MemberDaoImpl {

//	▼ 변수 ===============================================================
	
	final String NAMESPACE = "com.sist.mar.member";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
//	▼ 생성자 ==============================================================

	public MemberDaoImpl() {}
	
//	▼ 메소드 ===============================================================
	
	public int doRegister(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.insert(this.NAMESPACE + ".doRegister", dto);
	}
	
	public int doLoginCheck(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectOne(this.NAMESPACE + ".doLoginCheck", dto);
	}
	
	public int doCheckDuplicatedId(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectOne(this.NAMESPACE + ".doCheckDuplicatedId", dto);
	}
	
	public MemberVO doSelectOne(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.selectOne(this.NAMESPACE + ".doSelectOne", dto);
	}
	
	public int doUpdate(DTO dto) throws SQLException {
		return this.sqlSessionTemplate.update(this.NAMESPACE + ".doUpdate", dto);
	}
	
}
