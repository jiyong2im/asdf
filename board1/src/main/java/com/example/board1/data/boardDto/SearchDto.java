package com.example.board1.data.boardDto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private String keyword;
    private String userName;
}
