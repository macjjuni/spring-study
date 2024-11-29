package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    public MemberEntity getMemberById(int id){
        return memberMapper.getMemberById(id);
    }
    public void insertMember(MemberEntity memberEntity){
        memberMapper.insertMember(memberEntity);
    }
    public List<MemberEntity> getMemberList(){
        return memberMapper.getMemberList();
    }
}
