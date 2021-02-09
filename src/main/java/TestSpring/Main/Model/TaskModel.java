package TestSpring.Main.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Task")
public class TaskModel {

    @Id

    private String id;
    private String description;
    private String author;
    private String group;


}
