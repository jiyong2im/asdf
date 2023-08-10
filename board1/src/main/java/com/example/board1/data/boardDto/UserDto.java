package com.example.board1.data.boardDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String uid;
    private String password;
    private String name;


}
