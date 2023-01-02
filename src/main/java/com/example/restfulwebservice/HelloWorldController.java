package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // /hello-world (endpoint)
    // @RequestMapping(method=GRequestMethod.GET, path="/hello-world")
    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World";
    }


}
