package auth.api.estudos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_categoria")
    private UUID idCategoria;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "nome_categoria",nullable = false)
    private String nomeCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", orphanRemoval = true)
    private List<Produto> produtos;

    @PrePersist
    protected void aoCriar () {
        this.criadoEm = LocalDateTime.now();
    }

}
