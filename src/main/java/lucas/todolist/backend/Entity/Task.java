package lucas.todolist.backend.Entity;

import jakarta.persistence.*;

import lombok.*;
import lucas.todolist.backend.DTO.TaskRequestDTO;

import java.util.UUID;

@Entity(name = "tasks")
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    @Column(name = "user_email")
    private String userEmail;
    private String description;
    private Boolean completed;

    public Task(TaskRequestDTO taskRequestDTO) {
        this.name = taskRequestDTO.name();
        this.userEmail = taskRequestDTO.userEmail();
        this.description = taskRequestDTO.description();
        this.completed = false;
    }

    public void updateWithDTO(TaskRequestDTO taskRequestDTO) {
        this.name = taskRequestDTO.name();
        this.description = taskRequestDTO.description();
        this.userEmail = taskRequestDTO.userEmail();
        this.completed = taskRequestDTO.completed();
    }
}
