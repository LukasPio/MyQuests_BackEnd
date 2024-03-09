package lucas.todolist.backend.DTO;

public record TaskRequestDTO(String name, String userEmail, String description, Boolean completed) {
}
