package com.example.board1.controller;


import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardDto.Option;
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

    @GetMapping({"/","/list"})
    public Option getList(@RequestParam( value = "pageNo", defaultValue = "1", required = false)Long pageNo) {
        return boardService.selectList(pageNo.intValue());
    }
    @GetMapping("/list/{number}")
    public ResponseEntity<InsertDto> getOneList(@PathVariable("number") Long number, @RequestParam(value = "views", required = false) boolean views ) {
        int checked = 0;
        return ResponseEntity.ok(boardService.selectOneList(number, views, checked));
    }

    @GetMapping("/list/like/{number}")
    public ResponseEntity<InsertDto> getOneListLike(@PathVariable("number") Long number, @RequestParam(value = "checked", required = false) int checked ) {
        boolean views = false;
        return ResponseEntity.ok(boardService.selectOneList(number, views, checked));
    }
    @PostMapping("/list")
    public void setList(@RequestBody InsertDto insertDto){
        LOGGER.info("이것은 컨트롤러 요청 ");

        boardService.writeService(insertDto);

    }

    @PatchMapping("/list/{number}")
    public void modifyList(@RequestBody InsertDto insertDto, @PathVariable("number") Long number){
        boardService.updateService(insertDto, number);
    }


    @DeleteMapping("list/{number}")
    public void deleteOneList(@PathVariable("number") Long number){
        boardService.deleteService(number);
    }


}
