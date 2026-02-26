package com.example.app.service;

import java.util.List;

import com.example.app.domain.Member;
import com.example.app.domain.MemberType;

public interface MemberAdminService {

	//MemberServiceImplクラスの22行目で使用
	List<Member> getMemberList();
	//MemberServiceImplクラスの27行目で使用
	Member getMemberById(Integer id);
	
	// ページ分割機能用
	 List<Member> getMemberListByPage(int page, int numPerPage);
	 int getTotalPages(int numPerPage);

	
	void addMember(Member member);
	void editMember(Member member);
	void deleteMember(Integer id);
	List<MemberType> getTypeList();
}
