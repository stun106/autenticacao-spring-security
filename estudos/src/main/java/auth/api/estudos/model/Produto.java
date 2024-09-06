package auth.api.estudos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_produto")
    private UUID idProduto;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "criado_em",updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "criado_por")
    private String criadoPor;

    @ManyToOne
    private Categoria categoria;

    @PrePersist
    protected void aoCriar () {
        this.criadoEm = LocalDateTime.now();
    }
}
