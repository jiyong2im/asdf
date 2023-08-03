package com.example.board1.data.boardDao;


import com.example.board1.data.boardEntity.BoardEntity;

import java.util.List;

public interface BoardDao {
    List<BoardEntity> selectListBoard();
    void saveBoard(BoardEntity boardEntity);

}
