package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {

}
