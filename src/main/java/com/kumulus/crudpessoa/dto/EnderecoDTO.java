package com.kumulus.crudpessoa.dto;

import com.kumulus.crudpessoa.model.Endereco;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

    private Integer id;
    private String estado;
    private String cidade;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cep;
    private Integer idPessoa;

    public Endereco toEndereco() {
        Endereco end = new Endereco();
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

    // Construtores, getters, setters e outros métodos conforme necessário
}
