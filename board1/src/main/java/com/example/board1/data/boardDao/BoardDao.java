package com.example.board1.data.boardDao;

import com.example.board1.data.boardDto.CommentDto;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardDto.LikeDto;
import com.example.board1.data.boardEntity.*;
import com.example.board1.data.boardRepository.*;
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
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardDao.class);

    @Autowired
    public BoardDao(BoardRepository boardRepository, SearchRepository searchRepository, CommentRepository commentRepository,LikeRepository likeRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.searchRepository = searchRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }
    public void saveSearch(SearchEntity searchEntity){
        searchRepository.save(searchEntity);
    }
    public Page<BoardEntity> selectBoardList(int start) {
        PageRequest request = PageRequest.of((start -1),7, Sort.by(Sort.Order.desc("number")));

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
    public Page<CommentEntity> commentRequestDao(Long number, int start) {
        PageRequest request = PageRequest.of((start -1),7, Sort.by(Sort.Order.desc("number")));

        Page<CommentEntity> pageBoard = commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request);
        LOGGER.info("commentGetDao ,{}",commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request));
        return commentRepository.findAllByBoardEntity(boardRepository.findById(number).get(), request);

    }
    public void commentSaveDao(CommentDto commentDto){
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

    public InsertDto selectOneBard(Long number, boolean views, int checked, String username) {

        BoardEntity boardEntity = boardRepository.findById(number).get();
        User user = userRepository.findByUid(username);
        if(views) {
            boardEntity.setViews(boardEntity.getViews() + 1L);
            boardRepository.save(boardEntity);
        }else{
        boardEntity.setViews(boardEntity.getViews());}

        InsertDto insertDto = new InsertDto();

        if(checked == 1) {
//            LikeEntity likeEntity = new LikeEntity();
//            likeEntity.setCheckLikePush(true);
//            likeEntity.setUserId(user);
//            likeEntity.isCheckHatePush();
//// 두가지 조건
//// 1.
//            if(likeEntity.isCheckLikePush()|| likeEntity.getCheckLikePush()){
//
//            }
//            likeEntity.setBoardNumber(boardEntity);

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
    public void deleteCommentBoard(Long number) {
        commentRepository.deleteById(number);
    }
}



