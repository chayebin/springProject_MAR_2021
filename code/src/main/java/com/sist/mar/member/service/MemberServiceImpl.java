package com.sist.mar.member.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.mar.cmn.DTO;
import com.sist.mar.member.dao.MemberDaoImpl;
import com.sist.mar.member.domain.MemberVO;

@Service
public class MemberServiceImpl {
	
//	▼ 변수 ===============================================================

	@Autowired
	private MemberDaoImpl memberDao;
	
//	▼ 생성자 ==============================================================	
	
	public MemberServiceImpl() {}
	
	
//	▼ 메소드 ===============================================================	
	
	public int doRegister(DTO dto) throws SQLException {
		return memberDao.doRegister(dto);
	}
	
	public int doLoginCheck(DTO dto) throws SQLException {
		return memberDao.doLoginCheck(dto);
	}
	
	public int doCheckDuplicatedId(DTO dto) throws SQLException {
		return memberDao.doCheckDuplicatedId(dto);
	}
	
	public MemberVO doSelectOne(DTO dto) throws SQLException {
		return memberDao.doSelectOne(dto);
	}
	
	public int doUpdate(DTO dto) throws SQLException {
		return memberDao.doUpdate(dto);
	}
}
