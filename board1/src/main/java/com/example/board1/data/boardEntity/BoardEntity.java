package com.example.board1.data.boardEntity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    private String id;
    private String name;
    private String title;
    private String text;
    private Long views;
//    @ColumnDefault("0")
    private Long great;
//    @ColumnDefault("0")
    private Long hate;



}
