package lucas.todolist.backend.Controller;

import lucas.todolist.backend.DTO.TaskRequestDTO;
import lucas.todolist.backend.DTO.TaskResponseDTO;
import lucas.todolist.backend.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserEmail(@RequestParam String userEmail) {
       return taskService.getTasksByUserEmail(userEmail);
    }

    @GetMapping("/id")
    public ResponseEntity<UUID> getTaskId(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.getTaskId(taskRequestDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.saveTask(taskRequestDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask (@RequestBody TaskRequestDTO taskRequestDTO, @RequestParam UUID taskId) {
        return taskService.updateTask(taskRequestDTO, taskId);
    }
}
