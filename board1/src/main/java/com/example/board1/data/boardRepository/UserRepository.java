package com.example.board1.data.boardRepository;

import com.example.board1.data.boardEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
}
