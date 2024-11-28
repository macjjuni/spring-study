package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    public MemberEntity getMemberById(int id){
        return memberMapper.getMemberById(id);
    }
    public int insertMember(MemberEntity memberEntity){
        return memberMapper.insertMember(memberEntity);
    }
}
