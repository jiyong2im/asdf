package com.example.board1.data.boardEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    private String text;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

}
