package lucas.todolist.backend.domain;

import jakarta.persistence.*;

import lombok.*;
import lucas.todolist.backend.DTOs.UserRequestDTO;

import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String email;
    private String password;

    public User(UserRequestDTO userRequestDTO) {
        this.name = userRequestDTO.name();
        this.email = userRequestDTO.email();
        this.password = userRequestDTO.password();
    }
}
