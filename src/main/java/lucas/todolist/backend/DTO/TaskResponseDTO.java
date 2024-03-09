package lucas.todolist.backend.DTO;

import lucas.todolist.backend.Entity.Task;

import java.util.UUID;

public record TaskResponseDTO(String userEmail, String taskName, String description, Boolean completed, UUID id) {
    public TaskResponseDTO(Task task) {
        this(task.getUserEmail(), task.getName(), task.getDescription(), task.getCompleted(), task.getId());
   }
}
