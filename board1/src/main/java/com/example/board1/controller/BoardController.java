package com.example.board1.controller;


import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.service.BoardService;
import com.example.board1.service.Impl.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

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
    @GetMapping("/list/{number}")
    public ResponseEntity<InsertDto> getOneList(@PathVariable("number") Long number) {
        return ResponseEntity.ok(boardService.selectOneList(number));
    }

    @PostMapping("/list")
    public void setList(@RequestBody InsertDto insertDto){

        boardService.writeService(insertDto);

    }

    @PutMapping("/list/{number}")
    public void modifyBoard(@RequestBody InsertDto insertDto, @PathVariable("number") Long number){



    }


}
