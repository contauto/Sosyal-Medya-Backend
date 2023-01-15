package com.example.learningSpring.user;
import com.example.learningSpring.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    public void save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public Page<User> getUsers(User user,Pageable pageable){
        if(user!=null){
            return  userRepository.findByUsernameNot(user.getUsername(),pageable);
        }
        return  userRepository.findAll(pageable);
    }

    public User getByUsername(String username){
        User inDB=userRepository.findByUsername(username);
        if(inDB==null){
            throw new NotFoundException();
        }
        else{
            return  inDB;
        }
    }

}
