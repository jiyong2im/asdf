package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

}
