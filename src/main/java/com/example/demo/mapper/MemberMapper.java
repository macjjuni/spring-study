package com.example.demo.mapper;

import com.example.demo.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberEntity getMemberById(int id);
    int insertMember(MemberEntity memberEntity);
    List<MemberEntity> getMemberList();
}
