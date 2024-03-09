package lucas.todolist.backend.DTO;

import lucas.todolist.backend.Entity.User;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, String password) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
