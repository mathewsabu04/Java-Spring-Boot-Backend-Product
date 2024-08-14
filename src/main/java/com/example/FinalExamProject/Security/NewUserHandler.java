package com.example.FinalExamProject.Security;

import com.example.FinalExamProject.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class NewUserHandler implements Command<LoginRequest,LoginResponse> {

    private final CustomerUserRepository customerUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final LoginHandler loginHandler;

    public NewUserHandler(CustomerUserRepository customerUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, LoginHandler loginHandler) {
        this.customerUserRepository = customerUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginHandler = loginHandler;
    }

    @Override
    public ResponseEntity<LoginResponse> executes(LoginRequest input) {
        CustomUser  user = new CustomUser();
        user.setUsername(input.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
        customerUserRepository.save(user);
        return loginHandler.executes(input);
    }
}
