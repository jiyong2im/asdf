package com.example.board1.data.boardEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "likeandhate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @ManyToOne
    @JoinColumn
    private User userId;

    @ManyToOne
    @JoinColumn
    private BoardEntity boardNumber;

    private boolean checkLikePush;
    private boolean checkHatePush;
}
