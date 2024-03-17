package lucas.todolist.backend.DTOs;

public record TaskRequestDTO(String name, String userEmail, String description, Boolean completed) {
}
