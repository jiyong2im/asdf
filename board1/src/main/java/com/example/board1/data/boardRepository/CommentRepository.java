package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardEntity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    ArrayList<CommentEntity> findAllByNumber(Long number);
    ArrayList<CommentEntity> findAllByBoardEntity(BoardEntity number);
    long countByBoardEntity(BoardEntity number);
    Page<CommentEntity>  findAllByBoardEntity(BoardEntity number, Pageable pageable);

}
