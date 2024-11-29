package com.example.demo.controller;

import com.example.demo.config.GeneralException;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.enumeration.ResultCodeEnum;
import com.example.demo.service.MemberService;
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
}
