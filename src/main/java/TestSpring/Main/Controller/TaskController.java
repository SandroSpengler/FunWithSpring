package TestSpring.Main.Controller;

import TestSpring.Main.Model.TaskModel;
import TestSpring.Main.MongoRepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/tasks")
    public List<TaskModel> ListOfAllTasks() {

        List<TaskModel> taskList = this.taskRepo.findAll();

        return taskList;
    }


    @PostMapping(path = "task")
    public List<Object> createSingleTask(@RequestHeader("Content-Type") String contentType, @RequestBody TaskModel task) {


        this.taskRepo.findOne(Example.of(task));

        Optional<TaskModel> taskSearch = this.taskRepo.findOne(Example.of(task));


        if (taskSearch.isEmpty()) {

            TaskModel saveTask = new TaskModel();
            saveTask = task;
            this.taskRepo.save(saveTask);
            return List.of(saveTask);


        } else {
            return List.of(new ResponseEntity<Object>("Already Exists", HttpStatus.NOT_FOUND));

        }


    }


    private Boolean checkForJsonHeader(String contentType) {
        if (contentType.toLowerCase().equals("application/json")) {
            return true;
        } else {
            return false;
        }
    }

}
