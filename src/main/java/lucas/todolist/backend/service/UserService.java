package lucas.todolist.backend.service;

import lucas.todolist.backend.DTOs.UserAvatarDTO;
import lucas.todolist.backend.DTOs.UserRequestDTO;
import lucas.todolist.backend.domain.User;
import lucas.todolist.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> verifyIfLoginExists(String email, String password) {
        boolean isEmailRegistered = userRepository.existsByEmail(email);
        if (!isEmailRegistered) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Email was not found");
        User user = userRepository.findByEmail(email);
        if (!user.getPassword().equals(password)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid password");
        return ResponseEntity.status(HttpStatus.OK).body("Login is valid");
    }

    public ResponseEntity<String> createUser(UserRequestDTO userData) {
        boolean isEmailRegistered = userRepository.existsByEmail(userData.email());
        if (isEmailRegistered) {
            String message = "An account with email: " + userData.email() + " already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        User user = new User(userData);
        user.setAvatar("/images/userIcon.png");
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User login was created successfully.");
    }

    public ResponseEntity<String> updateUser(UserRequestDTO userData) {
        if (userRepository.existsByEmail(userData.email())) {
            User user = userRepository.findByEmail(userData.email());
            user.setName(userData.name());
            user.setEmail(userData.email());
            user.setPassword(userData.password());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User data was updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with email"+ userData.email() +" wasn't found.");
    }

    public ResponseEntity<String> deleteUser(UserRequestDTO userRequestDTO) {
        boolean isEmailRegistered = userRepository.existsByEmail(userRequestDTO.email());
        if (!isEmailRegistered) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Email isn't registered.");
        User user = userRepository.findByEmail(userRequestDTO.email());
        if (!user.getPassword().equals(userRequestDTO.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password.");
        }
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("User was deleted.");
    }

    public ResponseEntity<Map<String, String>> getUserName(String userEmail) {
        boolean isEmailRegistered = userRepository.existsByEmail(userEmail);
        if (!isEmailRegistered) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections
                    .singletonMap("error", "Email isn't registered."));
        }
        String userName = userRepository.findByEmail(userEmail).getName();
        Map<String, String> response = Collections.singletonMap("name", userName);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<String> verifyIfEmailIsRegistered(String email) {
        if (userRepository.existsByEmail(email)) return ResponseEntity.status(HttpStatus.OK)
                .body("Email is registered");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email was not found.");
    }

    public ResponseEntity<String> updateAvatar(UserAvatarDTO userData) {
        if (!userRepository.existsByEmail(userData.email())) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Email isn't registered");
        User userToUpdate = userRepository.findByEmail(userData.email());
        userToUpdate.setAvatar(userData.avatar());
        userRepository.save(userToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Avatar was changed.");
    }

    public ResponseEntity<String> getUserAvatar(String email) {
        if (!userRepository.existsByEmail(email)) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Email isn't registered");
        String avatar = userRepository.findByEmail(email).getAvatar();
        return ResponseEntity.status(HttpStatus.OK).body(avatar);
    }
}
