package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
