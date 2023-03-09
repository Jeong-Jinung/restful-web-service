package com.example.restfulwebservice.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/users/{id}")
    public EntityModel<Member> retrieveUser(@PathVariable int id) {
        Optional<Member> member = userRepository.findById(id);
        if (!member.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        EntityModel<Member> memberEntityModel = EntityModel.of(member.get());
        WebMvcLinkBuilder linkto = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        memberEntityModel.add(linkto.withRel("all-users"));
        return memberEntityModel;
    }


}
