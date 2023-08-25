package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardEntity.LikeEntity;
import com.example.board1.data.boardEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByBoardNumberAndUserId(BoardEntity boardNumber, User userId);
    LikeEntity findByBoardNumberAndUserId(BoardEntity boardNumber, User userId);
}
