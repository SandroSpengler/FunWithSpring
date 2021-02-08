package TestSpring.Main.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class TaskController {


    @RequestMapping(path = "/")
    public List<String> testFunction() {
        return List.of("Hello", "World");
    }


}
