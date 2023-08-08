package com.example.board1.service;

import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardDto.Option;
import com.example.board1.data.boardDto.Pagination;
import com.example.board1.service.Impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Objects;

public interface BoardService {
    Option selectList(int pageNo);
    InsertDto selectOneList(Long number, boolean views, int checked);
    void writeService(InsertDto insertDto);

     void updateService(InsertDto insertDto, Long number);
    void deleteService(Long number);
    ArrayList<Pagination> pagination(int pageNo);
}
