package com.kumulus.crudpessoa.dto;

import com.kumulus.crudpessoa.model.Endereco;
import com.kumulus.crudpessoa.model.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PessoaDTO {
    private Integer id;
    private String nome;
    private Date dataNascimento;
    private String sexo;
    private List<Endereco> enderecos;

    public Pessoa toPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(this.id);
        pessoa.setNome(this.nome);
        pessoa.setDataNascimento(this.dataNascimento);
        pessoa.setSexo(this.sexo);
        pessoa.setEnderecos(this.enderecos);

        return pessoa;
    }
}
