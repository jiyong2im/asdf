package com.example.board1.data.boardDao.lmpl;

import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.InsertDto;
import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardRepository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardDaoImpl implements BoardDao {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardDaoImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<BoardEntity> selectListBoard(int start) {
        PageRequest request = PageRequest.of((start -1),10, Sort.by(Sort.Order.desc("number")));

        Page<BoardEntity> pageBoard = boardRepository.findAll(request);


        return pageBoard;
    }
    public Long numRecodes(){
        Long count = boardRepository.count();
        return count;
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
        insertDto.setWriter(boardEntity.getName());
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



