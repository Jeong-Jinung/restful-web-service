package com.example.restfulwebservice.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class MemberJpaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<Member> retrieveAllUsers() {
        return userRepository.findAll();
    }


}
