package br.com.bruno.SpringBootJSP.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	@RequestMapping("/api/greetings")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
