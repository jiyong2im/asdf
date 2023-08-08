package com.example.board1.data.boardDao;


import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardEntity.BoardEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardDao {
    Page<BoardEntity> selectListBoard(int start) ;
    Long numRecodes();
    void saveBoard(BoardEntity boardEntity);
    void updateBoard(InsertDto insertDto, Long number);
    InsertDto selectOneBard(Long number, boolean views);
    void deleteBoard(Long number);
}
