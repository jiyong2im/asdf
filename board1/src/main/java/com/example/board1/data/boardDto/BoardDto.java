package com.example.board1.data.boardDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long number;
    private String id;
    private String name;
    private String title;
    private String text;
    private Long views;
    private String createdAt;
    private String updatedAt;

}

