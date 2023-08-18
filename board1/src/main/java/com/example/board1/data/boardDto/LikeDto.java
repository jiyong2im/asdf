package com.example.board1.data.boardDto;

import com.example.board1.data.boardEntity.BoardEntity;
import com.example.board1.data.boardEntity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class LikeDto {

    private Long number;
    private User userId;
    private BoardEntity boardNumber;
    private boolean checkLikePush;
    private boolean checkHatePush;

}


