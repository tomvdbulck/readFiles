package be.ordina.readfiles.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class SpringBootController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from the read file app";
    }


}
