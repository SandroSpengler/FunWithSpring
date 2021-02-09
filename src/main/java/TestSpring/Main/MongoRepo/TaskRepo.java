package TestSpring.Main.MongoRepo;

import TestSpring.Main.Model.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends MongoRepository<TaskModel, String> {

//    public abstract TaskModel findByDescription(String desc);

}
