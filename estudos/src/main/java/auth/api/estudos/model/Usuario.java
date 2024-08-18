package auth.api.estudos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(mappedBy = "usuario")
    private Credencial credencial;

    @Enumerated(value = EnumType.STRING)
    private Autorizacao role;

    @OneToMany(mappedBy = "usuario")
    private Set<Endereco> enderecos;
}
