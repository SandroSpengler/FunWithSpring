package TestSpring.Main.Model;


import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class TaskModel {

    @Id

    private String id;
    private String description;
    private String author;
    private String group;


}
