package com.scaler.productservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String defaultHello(){
        return "default hello";
    }

    @GetMapping("/say/{yourName}/{yourCity}")
    public String sayHello(@PathVariable("yourName") String name, @PathVariable("yourCity")String city){
        return "Hey " + name + ", you are from " + city;
    }

    @GetMapping("/add")
    public String addNumbers(){
        return String.valueOf(2 + 3);
    }
}
