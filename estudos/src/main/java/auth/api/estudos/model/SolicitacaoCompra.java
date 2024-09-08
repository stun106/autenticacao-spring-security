package auth.api.estudos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "solicitacao_compra")
public class SolicitacaoCompra {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_solicitacao_compra")
    private UUID idSolicitacaoCompra;

    @Column(name = "data_uso")
    private String dataUso;

    private String motivo;

    private String comentario;

}
