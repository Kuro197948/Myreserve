package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Member;

public interface MemberMapper {

    List<Member> selectAll();

    Member selectById(Integer id);

    Member selectByName(String name);

    Member selectByEmail(String email);

    // ページ分割機能用
    List<Member> selectLimited(@Param("offset") int offset,
                               @Param("limit") int limit);

    Long count();

    void insert(Member member);

    void update(Member member);

    void updatePasswordById(@Param("id") Integer id,
                            @Param("loginPass") String loginPass);

    void delete(Integer id);
}