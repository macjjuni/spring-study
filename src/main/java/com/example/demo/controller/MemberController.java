package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members/{id}")
    public MemberDTO getMember(@PathVariable int id){
        return memberService.getMemberById(id);
    }

    @GetMapping("/")
    public String Hello() {
        return "Hello World!";
    }
}
