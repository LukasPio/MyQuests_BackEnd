package lucas.todolist.backend.controller;

import jakarta.validation.Valid;
import lucas.todolist.backend.DTOs.UserAvatarDTO;
import lucas.todolist.backend.DTOs.UserRequestDTO;
import lucas.todolist.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = {
        "https://myquests.vercel.app",
        "https://myquests.vercel.app/dist/home.js",
        "https://myquests.vercel.app/dist/login_page.js",
        "https://myquests.vercel.app/dist/register_page.js"
}, allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/name/{email}")
    public ResponseEntity<Map<String, String>> getUserName(@PathVariable String email) {
        return userService.getUserName(email);
    }

    @GetMapping(path = "/avatar")
    public ResponseEntity<String> getUserAvatar(@RequestParam String email) {
        return userService.getUserAvatar(email);
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

    @PatchMapping(path = "/avatar")
    public ResponseEntity<String> updateAvatar(@RequestBody UserAvatarDTO userData) {
        return userService.updateAvatar(userData);
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
