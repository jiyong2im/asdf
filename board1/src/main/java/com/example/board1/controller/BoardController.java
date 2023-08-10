package com.example.board1.controller;


import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardDto.Option;
import com.example.board1.data.boardEntity.User;
import com.example.board1.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    public BoardController(BoardService boardService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.boardService = boardService;
    }

    @GetMapping({"/","/list"})
    public Option getList(@RequestParam( value = "pageNo", defaultValue = "1", required = false)Long pageNo) {
        return boardService.selectList(pageNo.intValue());
    }

    @PostMapping("/login")
    public void login(User user){
        String id = user.getUid();
        String pswd = user.getPassword();

    }
    @PostMapping("/signup")
    public void signup(User user){
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        //save
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
