package lucas.todolist.backend.controller;

import jakarta.validation.Valid;
import lucas.todolist.backend.DTOs.UserRequestDTO;
import lucas.todolist.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/name/{email}")
    public ResponseEntity<Map<String, String>> getUserName(@PathVariable String email) {
        return userService.getUserName(email);
    }

    @GetMapping(path = "/verifyEmail")
    public ResponseEntity<String> verifyIfEmailIsRegistered(@RequestParam String email) {
        return userService.verifyIfEmailIsRegistered(email);
    }

    @GetMapping(path = "/verifyLogin")
    public ResponseEntity<String> verifyIfLoginExists(@RequestParam String email, @RequestParam String password) {
        return userService.verifyIfLoginExists(email, password);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userData) {
        return userService.createUser(userData);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userRequestDTO);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.deleteUser(userRequestDTO);
    }
}
