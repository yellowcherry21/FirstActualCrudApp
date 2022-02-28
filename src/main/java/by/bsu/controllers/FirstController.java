package by.bsu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/first")
    public String firstPage(){
        return "test/first";
    }

    @GetMapping("/second")
    public String secondPage(){
        return "test/second";
    }
}
