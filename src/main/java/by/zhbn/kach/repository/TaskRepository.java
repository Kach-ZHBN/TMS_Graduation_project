package by.zhbn.kach.repository;

import by.zhbn.kach.model.Person;
import by.zhbn.kach.model.Project;
import by.zhbn.kach.model.Task;
import by.zhbn.kach.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//    List<Task> findAllByCreatedAtBetween(Timestamp startDate, Timestamp endDate);
//
//    List<Task> findTasksByUsersAndCreatedAtBetween (Person users, Timestamp startDate, Timestamp endDate);
//
//    List<Task> findTasksByUsersOrderByCreatedAtDesc(User user);
//
//    Task findTasksById(Long id);

    List<Task> findAllByTaskExecutorOrderById(Person taskExecutor);

    List<Task> findAllByProject(Project project);

    long countByTaskExecutorAndCreatedTimeBetween(Person taskExecutor,
                                                  Timestamp startTime, Timestamp endTime);

    long countByTaskExecutorAndFinishedTimeBetween(Person taskExecutor,
                                                   Timestamp startTime, Timestamp endTime);

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);

}
