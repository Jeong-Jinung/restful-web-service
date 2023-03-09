package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<Member> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/1 or users/10
    @GetMapping("/users/{id}")
    public EntityModel<Member> retrieveUser(@PathVariable int id) {
        Member member = service.findOne(id);

        if (member == null) {
            throw new UserNotFoundException(String.format("ID[%s]) not found", id));
        }

        EntityModel<Member> model = EntityModel.of(member);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()
        );

        model.add(linkBuilder.withRel("all-users"));

        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Member> createUser(@Valid @RequestBody Member member) {
        Member savedMember = service.save(member);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id) {
        Member member = service.deleteById(id);

        if (member == null) {
            throw new UserNotFoundException(String.format("ID[%s]) not found", id));
        }
    }






}
