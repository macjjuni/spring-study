package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public MemberDTO getMemberById(int id) {
        Member member = memberMapper.getMemberById(id);

        return MemberDTO.fromMember(member); // DTO 변환
    }

}
