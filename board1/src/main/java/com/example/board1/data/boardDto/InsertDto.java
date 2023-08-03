package com.example.board1.data.boardDto;

import lombok.*;
import org.springframework.core.annotation.AliasFor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertDto {

    private String title;
    private String contents;
    private String writer;
}
