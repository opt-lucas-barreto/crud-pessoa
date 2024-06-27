package com.kumulus.crudpessoa.dto;

import com.kumulus.crudpessoa.model.Endereco;
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
}
