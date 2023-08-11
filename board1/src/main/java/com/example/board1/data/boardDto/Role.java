package com.example.board1.data.boardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Role {

    ROLE_USER("user");

    private String value;
}
