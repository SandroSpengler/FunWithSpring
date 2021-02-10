package TestSpring.Main.Controller;

import TestSpring.Main.Model.TaskModel;
import TestSpring.Main.MongoRepo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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

        this.dateTest();

        return List.of("Hello", "World");
    }

    @GetMapping(path = "/tasks")
    public List<TaskModel> ListOfAllTasks() {

        List<TaskModel> taskList = this.taskRepo.findAll();

        return taskList;
    }

    @GetMapping(path = "task")
    public List<Object> findSingleTask(@RequestParam String taskId) {

        TaskModel savedTask = this.taskRepo.findById(taskId).get();

        if (this.taskRepo.findById(taskId).isPresent()) {

            return List.of(savedTask);

        } else {

            return List.of(new ResponseEntity<Object>("Could find Task with the provied ID", HttpStatus.NOT_FOUND));
        }

    }


    @PostMapping(path = "task")
    public List<Object> createSingleTask(@RequestHeader("Content-Type") String contentType, @RequestBody TaskModel task) {

        Optional<TaskModel> taskSearch = this.taskRepo.findOne(Example.of(task));


        if (taskSearch.isEmpty()) {

            TaskModel savedTask = new TaskModel();
            savedTask = task;
            this.taskRepo.save(savedTask);
            return List.of(new ResponseEntity<Object>(savedTask, HttpStatus.ACCEPTED));

        } else {
            return List.of(new ResponseEntity<Object>("Already Exists", HttpStatus.CONFLICT));

        }

    }

    @PutMapping(path = "task")
    public List<Object> updateTask(@RequestParam String taskId, @RequestBody TaskModel task) {

        TaskModel savedTask = this.taskRepo.findById(taskId).get();


        if (this.taskRepo.findById(taskId).isPresent()) {
            savedTask.setDescription(task.getDescription());
            savedTask.setAuthor(task.getAuthor());
            savedTask.setGroup(task.getGroup());
            savedTask.setCreatedDate(task.getCreatedDate());
            savedTask.setDueDate(task.getDueDate());

            this.taskRepo.save(savedTask);

            return List.of(new ResponseEntity<Object>(savedTask, HttpStatus.ACCEPTED));

        } else {

            return List.of(new ResponseEntity<Object>("Could not Modify", HttpStatus.BAD_REQUEST));

        }

    }


    @DeleteMapping(path = "task")
    public List<Object> removeTask(@RequestParam String taskId) {


        TaskModel savedTask = this.taskRepo.findById(taskId).get();

        if (this.taskRepo.findById(taskId).isPresent()) {

            this.taskRepo.deleteById(taskId);

            return List.of(new ResponseEntity<Object>("Task deleted", HttpStatus.ACCEPTED));

        } else {

            return List.of(new ResponseEntity<Object>("Could not find Task by ID", HttpStatus.NOT_FOUND));
        }

    }


    private Boolean checkForJsonHeader(String contentType) {
        if (contentType.toLowerCase().equals("application/json")) {
            return true;
        } else {
            return false;
        }
    }


    private void dateTest() {

        LocalDateTime exampleDate;

        String ownDateString = "2018-05-05T11:50:55";

        exampleDate = LocalDateTime.now();
        System.out.println(exampleDate);

        exampleDate = LocalDateTime.parse(ownDateString);
        System.out.println(exampleDate);

    }


}
