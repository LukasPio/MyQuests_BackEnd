package lucas.todolist.backend.DTOs;

import lucas.todolist.backend.domain.User;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, String password, String avatar) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAvatar());
    }
}
