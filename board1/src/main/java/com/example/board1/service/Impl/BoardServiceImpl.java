package com.example.board1.service.Impl;


import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.BoardDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);


    @Autowired
    public BoardServiceImpl(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public ArrayList<BoardDto> selectList() {
        List<BoardEntity> boardList= boardDao.selectListBoard();
        ArrayList<BoardDto> boardDtoList = new ArrayList<>();

        List<BoardDto> l = boardList.stream().map(d -> {
            BoardDto dto = new BoardDto();
            dto.setName(d.getName());
            dto.setId(d.getId());
            dto.setTitle(d.getTitle());
            dto.setViews(d.getViews());
            dto.setText(d.getText());
            dto.setCreatedAt(d.getCreatedAt());
            dto.setUpdatedAt(d.getUpdatedAt());
            dto.setNumber(d.getNumber());
            return dto;
        }).toList();

        for(BoardEntity list :boardList) {
            BoardDto dto = new BoardDto();
            dto.setName(list.getName());
            dto.setId(list.getId());
            dto.setTitle(list.getTitle());
            dto.setViews(list.getViews());
            dto.setText(list.getText());
            dto.setCreatedAt(list.getCreatedAt());
            dto.setUpdatedAt(list.getUpdatedAt());
            dto.setNumber(list.getNumber());
            LOGGER.info("list 값 "+ dto.getNumber());

            boardDtoList.add(dto);
            LOGGER.info("list 값 "+ list.getName());

        }
        return boardDtoList;
    }
    public InsertDto selectOneList(Long number){
        InsertDto insertDto = new InsertDto();
        insertDto = boardDao.selectOneBard(number);
        return insertDto;
    }

    public void writeService(InsertDto insertDto) {
        //아이디 아직...
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(insertDto.getWriter());
        boardEntity.setText(insertDto.getContents());
        boardEntity.setTitle(insertDto.getTitle());
        boardEntity.setViews(0L);
        LOGGER.info("이것은 작성자입니다" + insertDto.getWriter());
        LOGGER.info("이것은 컨텐츠" + insertDto.getContents());
        LOGGER.info("이것은 제목" + insertDto.getTitle());
        boardDao.saveBoard(boardEntity);
    }

    public void updateService(InsertDto insertDto, Long number){
        boardDao.updateBoard(insertDto, number);
    }
}
