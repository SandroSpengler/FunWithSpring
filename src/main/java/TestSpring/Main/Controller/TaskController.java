package TestSpring.Main.Controller;

import TestSpring.Main.Model.TaskModel;
import TestSpring.Main.MongoRepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TaskController {

    public TaskRepo taskRepo;

    @Autowired
    public TaskController(TaskRepo taskRepo) {
        super();
        this.taskRepo = taskRepo;
    }


    @GetMapping(path = "/")
    public List<String> testFunction() {

        System.out.println("Hey");
        return List.of("Hello", "World");
    }

    @GetMapping(path = "/mongo")
    public List<TaskModel> searchMongo() {

        List<TaskModel> taskList = this.taskRepo.findAll();

        return taskList;
    }


}
