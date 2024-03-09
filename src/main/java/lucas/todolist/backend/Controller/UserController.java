package lucas.todolist.backend.Controller;

import lucas.todolist.backend.DTO.UserRequestDTO;
import lucas.todolist.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public ResponseEntity<String> getUserName(@RequestParam String email) {
        return userService.getUserName(email);
    }

    @GetMapping("/verifyLogin")
    public ResponseEntity<String> verifyIfLoginExists(@RequestParam String email, @RequestParam String password) {
        return userService.verifyIfLoginExists(email, password);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userData) {
        return userService.createUser(userData);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userRequestDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.deleteUser(userRequestDTO);
    }
}
