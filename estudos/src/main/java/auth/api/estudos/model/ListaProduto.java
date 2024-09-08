package auth.api.estudos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lista_produto")
public class ListaProduto {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_produto")
    private UUID idProduto;

    private double quantidade;

    @Column(name = "link_sujestao")
    private String linkSugestao;

   @OneToMany(mappedBy = "listaProduto")
    private List<Produto> produtos;
}
