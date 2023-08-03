package com.example.board1.data.boardDao.lmpl;

import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardRepository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardDaoImpl implements BoardDao {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardDaoImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardEntity> selectListBoard() {
        return boardRepository.findAll();
    }
    void saveBoard(BoardEntity boardEntity) {
        boardRepository.save(boardEntity);
    }
}



