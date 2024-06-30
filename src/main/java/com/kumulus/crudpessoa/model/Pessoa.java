package com.kumulus.crudpessoa.model;

import com.kumulus.crudpessoa.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
    @SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private String sexo;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Endereco> enderecos;

    public PessoaDTO toPessoaDTO() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(this.id);
        pessoaDTO.setNome(this.nome);
        pessoaDTO.setDataNascimento(this.dataNascimento);
        pessoaDTO.setSexo(this.sexo);
        pessoaDTO.setEnderecos(this.enderecos.stream().map(Endereco::toEnderecoDTO).collect(Collectors.toList()));
        return pessoaDTO;
    }
}
