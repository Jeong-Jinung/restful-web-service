package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Member> createUser(@Valid @RequestBody Member member) {
        Member save = userRepository.save(member);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(save.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }


}
