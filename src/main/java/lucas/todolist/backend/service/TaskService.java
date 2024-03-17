package lucas.todolist.backend.service;

import lucas.todolist.backend.DTOs.TaskRequestDTO;
import lucas.todolist.backend.DTOs.TaskResponseDTO;
import lucas.todolist.backend.domain.Task;
import lucas.todolist.backend.repository.TaskRepository;
import lucas.todolist.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    public boolean hasUserTaskWithProvidedName(String email, String taskName) {
        for (Task task: taskRepository.findTasksByUserEmail(email)) {
            if (isStringEqualWithoutSpace(task.getName(), taskName)) return true;
        }
        return false;
    }

    public boolean isStringEqualWithoutSpace(String aString, String otherString) {
        StringBuilder aStringWithoutSpace = new StringBuilder();
        StringBuilder otherStringWithoutSpace = new StringBuilder();

        for (int i = 0; i < aString.length(); i++) {
            if (aString.charAt(i) != ' ') aStringWithoutSpace.append(aString.charAt(i));
        }

        for (int i = 0; i < otherString.length(); i++) {
            if (otherString.charAt(i) != ' ') otherStringWithoutSpace.append(otherString.charAt(i));
        }

        return aStringWithoutSpace.toString().contentEquals(otherStringWithoutSpace);
    }

    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserEmail(String userEmail) {
        boolean loginExists = userRepository.existsByEmail(userEmail);
        if (!loginExists) return ResponseEntity.badRequest().build();
        List<TaskResponseDTO> taskList = taskRepository.findTasksByUserEmail(userEmail).stream()
                .map(TaskResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    public ResponseEntity<String> saveTask(TaskRequestDTO taskRequestDTO) {
        String taskName = taskRequestDTO.name(), userEmail = taskRequestDTO.userEmail();
        if (!userRepository.existsByEmail(userEmail)) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Email isn't registered.");
        if (hasUserTaskWithProvidedName(userEmail, taskName))  return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("User already have a task with name: " + taskName);
        taskRepository.save(new Task(taskRequestDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Task saved successfully.");
    }

    public ResponseEntity<UUID> getTaskId(TaskRequestDTO taskRequestDTO) {
        boolean isEmailRegistered = userRepository.existsByEmail(taskRequestDTO.userEmail());
        if (!isEmailRegistered) return ResponseEntity.badRequest().build();
        List<Task> taskList = taskRepository.findTasksByUserEmail(taskRequestDTO.userEmail());
        for (Task task: taskList) {
            if (task.getName().equals(taskRequestDTO.name())) return ResponseEntity.ok(task.getId());
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<String> updateTask(TaskRequestDTO taskRequestDTO, UUID taskId) {
        boolean isEmailRegistered = userRepository.existsByEmail(taskRequestDTO.userEmail());
        if (!isEmailRegistered) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email isn't registered.");
        Task taskToUpdate = taskRepository.findTasksById(taskId);
        taskToUpdate.updateWithDTO(taskRequestDTO);
        taskRepository.save(taskToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Task was updated successfully.");
    }

    public ResponseEntity<String> removeTask(TaskRequestDTO taskData) {
        List<Task> taskList = taskRepository.findTasksByUserEmail(taskData.userEmail());
        for (Task task: taskList) {
            if (task.getName().equals(taskData.name())) {
                taskRepository.delete(task);
                return ResponseEntity.status(HttpStatus.OK).body("Task was deleted.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any tasks with provided name was found.");
    }
}
