package com.example.board1.controller;


import com.example.board1.data.boardDto.*;
import com.example.board1.data.boardEntity.User;
import com.example.board1.service.BoardService;
import com.example.board1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/")
@RestController
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    public BoardController(BoardService boardService, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.boardService = boardService;
    }

    //검색 하기, 유저 검색어 저장
    @GetMapping("/search")
    public ArrayList<BoardDto> getSearch(@RequestParam(value = "word", required = false) String word, @AuthenticationPrincipal UserDetails userDetails) {
        try {
                SearchDto searchDto = new SearchDto(word, userDetails.getUsername());
                boardService.searchService(searchDto);
                return boardService.selectSearchList(word);
        } catch (Exception e) {
            return boardService.selectSearchList(word);
        }
    }

    @GetMapping("/comment")
    public Option getComment(@RequestParam(value = "number", required = false) Long number,@RequestParam(value = "pageNo", defaultValue = "1", required = false) Long pageNo){
        return boardService.commentGetService(number, pageNo.intValue());
    }
    @PostMapping("/comment")
    public void setComment(@RequestBody CommentDto commentDto) {
        LOGGER.info("컨트롤러 댓글 ,{}",commentDto.getContent());
        boardService.commentSaveService(commentDto);


    }

    @GetMapping("/login")
    public void login( User user){
    }

    @GetMapping("checkedLogin")
    public UserDetails checkedLogin(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping("/logout")
    public void logout() {
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDto userdto, BindingResult bindingResult){
        LOGGER.info("이것 login 요청"+ userdto.getUid());
        //아직...

        if(bindingResult.hasErrors()){

        }
        String rawPassword = userdto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userdto.setPassword(encPassword);
        LOGGER.info("이것 signup 요청 ");

        userService.saveUser(userdto);

        //save
    }

    @GetMapping({"/", "/list"})
    public Option getList(@RequestParam(value = "pageNo", defaultValue = "1", required = false) Long pageNo,
                          @AuthenticationPrincipal UserDetails userDetails
    ) {
        LOGGER.info("이것 login 요청, {}", userDetails);
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
