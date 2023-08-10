package com.example.board1.data.boardDto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    private String display;
    private int pageNo;
    private boolean curPage;
}
