package com.example.board1.service.Impl;

import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.UserDto;
import com.example.board1.data.boardEntity.User;
import com.example.board1.data.boardRepository.UserRepository;
import com.example.board1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = userRepository.findByUid(uid);
        if(user ==null){
            throw new UsernameNotFoundException(uid);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUid())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();

    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void saveUser(UserDto userDto){
        validateUser(userDto);
    }
    private void validateUser(UserDto userDto){
        User finduser = userRepository.findByUid(userDto.getUid());
        if(finduser != null){
            throw new IllegalStateException("이미 가입된 사용자");
        }else{
            saveUser(User.createUser(userDto));
        }
    }



}
