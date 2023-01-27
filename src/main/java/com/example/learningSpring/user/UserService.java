package com.example.learningSpring.user;

import com.example.learningSpring.error.NotFoundException;
import com.example.learningSpring.file.FileService;
import com.example.learningSpring.user.Dtos.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    FileService fileService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }


    public void save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public Page<User> getUsers(User user, Pageable pageable) {
        if (user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), pageable);
        }
        return userRepository.findAll(pageable);
    }

    public User getByUsername(String username) {
        User inDB = userRepository.findByUsername(username);
        if (inDB == null) {
            throw new NotFoundException();
        } else {
            return inDB;
        }
    }

    public User updateUser(String username, UserUpdateDto userUpdateDto) {
        User inDB = getByUsername(username);
        inDB.setName(userUpdateDto.getName());
        if (userUpdateDto.getImage() != null) {
            String oldImageName = inDB.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(userUpdateDto.getImage());
                inDB.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleteProfilePhoto(oldImageName);
        }
        return userRepository.save(inDB);

    }

    public void deleteUser(String username) {
        User inDB = userRepository.findByUsername(username);
        fileService.deleteAllStoredFilesForUser(inDB);
        userRepository.delete(inDB);
    }

}
