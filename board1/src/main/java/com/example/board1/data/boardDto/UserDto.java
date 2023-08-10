package com.example.board1.data.boardDto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
//    @Length(min = 4, max =20, message = "아이디는 4자 이상, 20자 이하로")
    @NotBlank(message = "아이디를 입력해 주세요")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해 주세요")
//    @Length(min = 8, max =20, message = "비밀번호는 8자 이상, 20자 이하로")
    private String password;

//    @NotBlank(message = "이름을 입력해 주세요")
    private String name;


}
