package com.example.demo.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int id;
    private String name;
    private String password;
}
