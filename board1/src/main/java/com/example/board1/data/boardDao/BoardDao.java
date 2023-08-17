package com.example.board1.data.boardDao;

import com.example.board1.data.boardDto.CommentDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardEntity.CommentEntity;
import com.example.board1.data.boardEntity.SearchEntity;
import com.example.board1.data.boardRepository.BoardRepository;
import com.example.board1.data.boardRepository.CommentRepository;
import com.example.board1.data.boardRepository.SearchRepository;
import com.example.board1.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao {
    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    private final SearchRepository searchRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardDao.class);

    @Autowired
    public BoardDao(BoardRepository boardRepository, SearchRepository searchRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.searchRepository = searchRepository;
        this.commentRepository = commentRepository;
    }
    public void searchBoard(SearchEntity searchEntity){
        searchRepository.save(searchEntity);
    }
    public Page<BoardEntity> selectListBoard(int start) {
        PageRequest request = PageRequest.of((start -1),10, Sort.by(Sort.Order.desc("number")));

        Page<BoardEntity> pageBoard = boardRepository.findAll(request);


        return pageBoard;
    }
    public ArrayList<BoardEntity> searchList(String word){
        return boardRepository.findAllSearch(word);
    }
    public Long numRecodes(){
        Long count = boardRepository.count();
        return count;
    }
    public Long commentNumRecodes(Long number) {
        LOGGER.info("commentGetDao ,{}",commentRepository.countByBoardEntity(boardRepository.findById(number).get()));
        Long count = commentRepository.countByBoardEntity(boardRepository.findById(number).get());
        return count;
    }
    public Long numSearchRecodes(String word){
        Long count = boardRepository.findSearchCount(word);
        return count;
    }
    public Page<CommentEntity> commentGetDao(Long number, int start) {
        PageRequest request = PageRequest.of((start -1),10, Sort.by(Sort.Order.desc("number")));

        Page<CommentEntity> pageBoard = commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request);
        LOGGER.info("commentGetDao ,{}",commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request));
        return commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request);

    }
    public void commentDao(CommentDto commentDto){
        CommentEntity comment = new CommentEntity();
        BoardEntity boardEntity = boardRepository.findById(commentDto.getNumber()).get();
        comment.setBoardEntity(boardEntity);
        LOGGER.info("값 ,{}",boardEntity);
        comment.setUserId(commentDto.getUsername());
        LOGGER.info("값 ,{}",commentDto.getUsername());
        comment.setText(commentDto.getContent());
        LOGGER.info("값 ,{}",commentDto.getContent());
        commentRepository.save(comment);
    }

    public InsertDto selectOneBard(Long number, boolean views, int checked) {

        BoardEntity boardEntity = boardRepository.findById(number).get();
        if(views) {
            boardEntity.setViews(boardEntity.getViews() + 1L);
            boardRepository.save(boardEntity);
        }else{
        boardEntity.setViews(boardEntity.getViews());}

        InsertDto insertDto = new InsertDto();

        if(checked == 1) {
            insertDto.setGreat(boardEntity.getGreat()+1L);
            insertDto.setHate(boardEntity.getHate());
            boardEntity.setGreat(insertDto.getGreat());
            boardRepository.save(boardEntity);

        }if(checked == 2){
            insertDto.setHate(boardEntity.getHate() +1L );
            insertDto.setGreat(boardEntity.getGreat());
            boardEntity.setHate(insertDto.getHate());
            boardRepository.save(boardEntity);
        }else {
            insertDto.setGreat(boardEntity.getGreat());
            insertDto.setHate(boardEntity.getHate());
        }

        insertDto.setTitle(boardEntity.getTitle());
        insertDto.setContents(boardEntity.getText());
        insertDto.setWriter(boardEntity.getId());
        insertDto.setViews(boardEntity.getViews());
        insertDto.setCreatedAt((boardEntity.getCreatedAt().toString().replace("-", ":")
                .replace("T","/").substring(0,16)));

        return insertDto;
    }
    public void saveBoard(BoardEntity boardEntity) {
        boardRepository.save(boardEntity);
    }

    public void updateBoard(InsertDto insertDto, Long number){

        BoardEntity boardEntity = boardRepository.findById(number).get();
        boardEntity.setName(insertDto.getWriter());
        boardEntity.setText(insertDto.getContents());
        boardEntity.setTitle(insertDto.getTitle());
        boardRepository.save(boardEntity);
    }

    public void deleteBoard(Long number) {
        boardRepository.deleteById(number);
    }
}



