package com.kumulus.crudpessoa.dto;

import com.kumulus.crudpessoa.model.Endereco;
import lombok.*;

@Data
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
        if(this.cep != null)
            end.setCep(this.cep.replaceAll("[^0-9]", ""));
        else
            end.setCep("");
        end.setIdPessoa(this.idPessoa);
        return end;
    }

    // Construtores, getters, setters e outros métodos conforme necessário
}
