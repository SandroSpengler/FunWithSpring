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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
