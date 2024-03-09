package lucas.todolist.backend.Repository;

import lucas.todolist.backend.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findTasksByUserEmail(String userEmail);
    Task findTasksById(UUID id);
}
