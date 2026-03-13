package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Member {

    private Integer id;
    private String name;
    private String email;
    private String loginPass;
    private Integer typeId;
    private LocalDateTime created;

    private MemberType type;
}