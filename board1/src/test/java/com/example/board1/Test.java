package com.example.board1;

import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardRepository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class Test {
    @Autowired
    BoardRepository boardRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @DisplayName("실험")
    @org.junit.jupiter.api.Test
    public void test1() {
        ArrayList<BoardEntity> dd = boardRepository.findAllSearch("v");
        for(BoardEntity aa :dd){
            LOGGER.info("das, {}",aa.getTitle());
        }
        LOGGER.info("das, {}",dd);

    }
}
