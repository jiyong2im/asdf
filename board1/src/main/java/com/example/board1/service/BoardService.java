package com.example.board1.service;

import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.service.Impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Objects;

public interface BoardService {
    ArrayList<BoardDto> selectList();
    void writeService(InsertDto insertDto);

}
