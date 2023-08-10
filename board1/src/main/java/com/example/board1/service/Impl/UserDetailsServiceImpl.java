//package com.example.board1.service.Impl;
//
//import com.example.board1.data.boardRepository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//// ?
//@RequiredArgsConstructor
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    //리턴 타입 UserDetails인데 이걸 implements User 엔티티 값을 보냄
//    @Override
//    public UserDetails loadUserByUsername(String username){
//        return userRepository.getByUid(username);
//    }
//}
