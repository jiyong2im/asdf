package com.example.board1.controller;


import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/")
@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> getList() {
//        Long pageNo = num;
//        List<BoardDto> boardList = boardService.selectList(pageNo.intValue());
        return ResponseEntity.ok(boardService.selectList());
    }

    @PostMapping("/board")
    public void setList(InsertDto insertDto){
        boardService.writeService(InsertDto);

    }

}
