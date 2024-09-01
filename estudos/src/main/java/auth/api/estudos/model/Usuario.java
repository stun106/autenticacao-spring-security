package auth.api.estudos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(generator = "UUID")
    private UUID idUsuario;

    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    @Enumerated(value = EnumType.STRING)
    private Autorizacao role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> endereco;

    @PrePersist
    protected void aoCriar () {
        this.criadoEm = LocalDateTime.now();
    }
}
