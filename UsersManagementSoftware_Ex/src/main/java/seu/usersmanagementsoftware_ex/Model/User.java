package seu.usersmanagementsoftware_ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The name must be not Empty")
    @Size(min = 5 , message = "The name must be more than 4 ")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "The username must be not empty")
    @Size(min = 5 , message = "The username must be more than 4 ")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;

    @NotEmpty(message = "The password must be not empty")
    @Column(columnDefinition = "varchar(30)")
    private String password;

    @NotEmpty(message = "The email must be not empty")
    @Email(message = "The email must be valid email ")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "The role must be not empty")
    @Pattern(regexp = "^(user|admin)$", message = "Role must be either 'user' or 'admin'")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "The age must be not null")
    @Positive(message = "The age must be integer ")
    @Column(columnDefinition = "int not null")
    private Integer age;


}
