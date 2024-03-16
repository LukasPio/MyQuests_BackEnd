package lucas.todolist.backend.controller;

import jakarta.validation.Valid;
import lucas.todolist.backend.DTOs.TaskRequestDTO;
import lucas.todolist.backend.DTOs.TaskResponseDTO;
import lucas.todolist.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api/task")
@CrossOrigin(origins = {
        "https://myquests.vercel.app",
        "https://myquests.vercel.app/dist/home.js",
        "https://myquests.vercel.app/dist/login_page.js",
        "https://myquests.vercel.app/dist/register_page.js"
}, allowedHeaders = "*")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/{email}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserEmail(@PathVariable String email) {
       return taskService.getTasksByUserEmail(email);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<UUID> getTaskId(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.getTaskId(taskRequestDTO);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.saveTask(taskRequestDTO);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateTask (@RequestBody TaskRequestDTO taskRequestDTO, @RequestParam UUID taskId) {
        return taskService.updateTask(taskRequestDTO, taskId);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> removeTask(@RequestBody TaskRequestDTO taskData) {
        return taskService.removeTask(taskData);
    }
}
