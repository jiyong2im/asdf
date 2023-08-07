package com.example.board1.data.boardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    private ArrayList<BoardDto> dto;
    private ArrayList<Pagination> pgn;
}
