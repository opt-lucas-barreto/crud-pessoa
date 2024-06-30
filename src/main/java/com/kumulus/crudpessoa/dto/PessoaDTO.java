package com.kumulus.crudpessoa.dto;

import com.kumulus.crudpessoa.model.Endereco;
import com.kumulus.crudpessoa.model.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PessoaDTO {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private List<EnderecoDTO> enderecos;

    public Pessoa toPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(this.id);
        pessoa.setNome(this.nome);
        pessoa.setDataNascimento(this.dataNascimento);
        pessoa.setSexo(this.sexo);
        // Verifica se enderecos é null antes de chamar stream()
        if (this.enderecos != null)
            pessoa.setEnderecos(this.enderecos.stream().map(EnderecoDTO::toEndereco).collect(Collectors.toList()));
        else
            pessoa.setEnderecos(new ArrayList<>());


        return pessoa;
    }
}
