package com.example.demo.mapper;

import com.example.demo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member getMemberById(int id);
}
