package com.example.board1.data.boardEntity;


import com.example.board1.data.Role;
import com.example.board1.data.boardDto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "user")

@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column
    private String name;

    @Column
    private String password;

    private Role role;

    public static User createUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setUid(userDto.getUid());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.USER);
        return  user;
    }

//
//    @OneToMany(mappedBy = BaseEntity)
//    @JoinColumn(name = "boardentity_number")
//    private ArrayList<BoardEntity> boardEntity = new ArrayList<>();

}
