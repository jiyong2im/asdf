package com.example.board1.service.Impl;

import com.example.board1.controller.BoardController;
import com.example.board1.data.boardDao.BoardDao;
import com.example.board1.data.boardDto.UserDto;
import com.example.board1.data.boardEntity.User;
import com.example.board1.data.boardRepository.UserRepository;
import com.example.board1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        log.debug("uid: {}", uid);
        User user = userRepository.findByUid(uid);
        if(user ==null){
            /*
            이때 프런트로 오류 보내야한다.
             */
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

//    public void login(User user) throws UsernameNotFoundException {
//        try {
//            LOGGER.info("트라이문 시작");
//            User member = userRepository.findByUid(user.getUid());
//            if (member.getPassword() == user.getPassword()) {
//                LOGGER.info("패스워드 일치");
//
//            }else{
//                LOGGER.info("패스워드 불일치");
//            }
//
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage()+"하이");
//        }
//    }

}
