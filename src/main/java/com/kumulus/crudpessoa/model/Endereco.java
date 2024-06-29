package com.kumulus.crudpessoa.model;

import com.kumulus.crudpessoa.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    @SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private Integer idPessoa;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false , insertable = false, updatable = false)
    private Pessoa pessoa;

    public EnderecoDTO toEnderecoDTO() {
        EnderecoDTO end = new EnderecoDTO();
        end.setId(this.id);
        end.setEstado(this.estado);
        end.setCidade(this.cidade);
        end.setLogradouro(this.logradouro);
        end.setNumero(this.numero);
        end.setBairro(this.bairro);
        end.setCep(this.cep);
        end.setIdPessoa(this.idPessoa);
        return end;
    }
}