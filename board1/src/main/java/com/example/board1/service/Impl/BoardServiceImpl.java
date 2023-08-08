package com.example.board1.service.Impl;


import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardDto.Option;
import com.example.board1.data.boardDto.Pagination;
import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
    private static final int paginationSize = 5;
    private static final int listSize = 10;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public Option selectList(int pageNo) {
        ArrayList<Pagination> pgnList = new ArrayList<>();

        Long numRecords = boardDao.numRecodes();

        int numPages = (int)Math.ceil((double)numRecords / listSize);
        int firstLink = ((pageNo - 1) / paginationSize) * paginationSize + 1;
        int lastLink = firstLink + paginationSize - 1;
        if (lastLink > numPages) {
            lastLink = numPages;
        }
        if (firstLink > 1) {
            pgnList.add(new Pagination("<<", firstLink - 1, false));
        }

        for (int i = firstLink; i <= lastLink; i++) {
            pgnList.add(new Pagination("" + i, i, i == pageNo));
        }

        if (lastLink < numPages) {
            pgnList.add(new Pagination(">>", lastLink + 1, false));
        }


        Page<BoardEntity> boardList= boardDao.selectListBoard(pageNo);
        ArrayList<BoardDto> boardDtoList = new ArrayList<>();

//        List<BoardDto> l = boardList.stream().map(d -> {
//            BoardDto dto = new BoardDto();
//            dto.setName(d.getName());
//            dto.setId(d.getId());
//            dto.setTitle(d.getTitle());
//            dto.setViews(d.getViews());
//            dto.setText(d.getText());
//            dto.setCreatedAt(d.getCreatedAt());
//            dto.setUpdatedAt(d.getUpdatedAt());
//            dto.setNumber(d.getNumber());
//            return dto;
//        }).toList();
        String str;
        for(BoardEntity list :boardList) {
            BoardDto dto = new BoardDto();
            dto.setName(list.getName());
            dto.setId(list.getId());
            dto.setTitle(list.getTitle());
            dto.setViews(list.getViews());
            dto.setText(list.getText());
            str = (list.getCreatedAt().toString().replace("-", ":")
                    .replace("T","/").substring(5,16));
            dto.setCreatedAt(str);
            str = (list.getUpdatedAt().toString().replace("-", ":")
                    .replace("T","/").substring(5,16));
            dto.setUpdatedAt(str);
            dto.setNumber(list.getNumber());

            boardDtoList.add(dto);
        }

        Option option =new Option();
        option.setDto(boardDtoList);
        option.setPgn(pgnList);

        return option;
    }


    public ArrayList<Pagination> pagination(int pageNo) {
        ArrayList<Pagination> pgnList = new ArrayList<>();

        Long numRecords = boardDao.numRecodes();

        int numPages = (int)Math.ceil((double)numRecords / listSize);
        int firstLink = ((pageNo - 1) / paginationSize) * paginationSize + 1;
        int lastLink = firstLink + paginationSize - 1;
        if (lastLink > numPages) {
            lastLink = numPages;
        }
        if (firstLink > 1) {
            pgnList.add(new Pagination("이전", firstLink - 1, false));
        }

        for (int i = firstLink; i <= lastLink; i++) {
            pgnList.add(new Pagination("" + i, i, i == pageNo));
        }

        if (lastLink < numPages) {
            pgnList.add(new Pagination("다음", lastLink + 1, false));
        }

        return pgnList;
    }
    public InsertDto selectOneList(Long number, boolean views, int checked){
        InsertDto insertDto;
        insertDto = boardDao.selectOneBard(number, views, checked);
        return insertDto;
    }

    public void writeService(InsertDto insertDto) {
        //아이디 아직...
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(insertDto.getWriter());
        boardEntity.setText(insertDto.getContents());
        boardEntity.setTitle(insertDto.getTitle());
        boardEntity.setGreat(0L);
        boardEntity.setHate(0L);
        boardEntity.setViews(0L);
        LOGGER.info("이것은 작성자입니다" + insertDto.getWriter());
        LOGGER.info("이것은 컨텐츠" + insertDto.getContents());
        LOGGER.info("이것은 제목" + insertDto.getTitle());
        boardDao.saveBoard(boardEntity);
    }

    public void updateService(InsertDto insertDto, Long number){
        boardDao.updateBoard(insertDto, number);
    }

    public void deleteService(Long number) {
        boardDao.deleteBoard(number);
    }
}
