package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query(value="select * from board b where b.title like %:word% order by number DESC", nativeQuery=true)
    ArrayList<BoardEntity> findAllSearch(String word);

    @Query(value="select COUNT(*) from board b where b.title like %:word%", nativeQuery=true)
    Long findSearchCount(String word);
}
