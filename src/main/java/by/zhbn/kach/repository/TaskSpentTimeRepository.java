package by.zhbn.kach.repository;

import by.zhbn.kach.model.Task;
import by.zhbn.kach.model.TaskSpentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskSpentTimeRepository extends JpaRepository<TaskSpentTime, Long> {

    List<TaskSpentTime> getAllByTaskAndStartedTimeIsAfterAndStoppedTimeIsBefore(Task task, Timestamp startTime, Timestamp endTime);

}
