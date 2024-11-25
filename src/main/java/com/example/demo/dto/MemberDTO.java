package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class MemberDTO {
    private int id;
    private String name;

    public static MemberDTO fromMember(Member member) {

        return new MemberDTO(member.getId(), member.getName());
    }
}
