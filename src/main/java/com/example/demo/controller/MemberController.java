package com.example.demo.controller;

import com.example.demo.config.GeneralException;
import com.example.demo.dto.LoginResultDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.enumeration.ResultCodeEnum;
import com.example.demo.service.MemberService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/")
    public String Hello() {
        return "Hello World!";
    }


    @GetMapping("/member/info/{id}")
    public ResponseDto<MemberDto> getMemberInfo(@PathVariable String id) {

        MemberEntity entity =
                Optional.ofNullable(memberService.getMemberById(Integer.parseInt(id)))
                        .orElseThrow(() -> new GeneralException(ResultCodeEnum.MemberNotFound));

        return ResponseDto.of(
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(),
                MemberDto.of(entity));
    }

    @GetMapping("/member/list")
    public ResponseDto<List<MemberDto>> getMemberList() {
        List<MemberEntity> entityList =
                Optional.ofNullable(memberService.getMemberList())
                        .orElseThrow(() -> new GeneralException(ResultCodeEnum.MemberNotFound));

        return ResponseDto.of(
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(),
                MemberDto.of(entityList));
    }


    @PostMapping("/member/register")
    public ResponseDto<MemberDto> registerMember(@RequestBody MemberDto.MemberInsertRequestDto req) {
        MemberEntity entity = MemberDto.memberInsertRequestDtoEntity(req);
        memberService.insertMember(entity);
        MemberDto memberDto = MemberDto.of(Optional.ofNullable(memberService.getMemberById(entity.getId()))
                .orElseThrow(() -> new GeneralException(ResultCodeEnum.MemberNotFound)));

        return ResponseDto.of(
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(),
                memberDto);
    }

    @PostMapping("/member/login")
    public ResponseDto<LoginResultDto> loginMember(@RequestBody MemberDto.MemberLoginRequestDto req) {

        // ID로 멤버 가져오기를 시도

        MemberEntity entity = memberService.getMemberById(req.getId());

        if (entity == null || !entity.getPassword().equals(req.getPassword())) {
            throw new GeneralException(ResultCodeEnum.LoginFAILED);
        }

        // 토큰 생성
        String token = JwtUtil.generateToken(MemberDto.of(entity));

        return ResponseDto.of(
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(),
                LoginResultDto.builder().token(token)
                        .expiration(JwtUtil.getExpireDate(token))
                        .member(MemberDto.of(entity))
                        .build());
    }

}
