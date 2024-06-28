package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

    @Inject
    private PessoaBusiness pessoaBusiness; // Assuming you have a service to fetch people

    @Getter @Setter
    private List<PessoaDTO> pessoas;

    @Getter @Setter
    private PessoaDTO pessoaSelecionada;

    @PostConstruct
    public void init() {
        pessoaSelecionada = new PessoaDTO();
        pessoas = pessoaBusiness.buscarTodos();
    }

    public String prepararEdicao(PessoaDTO pessoa) {
        this.pessoaSelecionada = pessoa;
        return null;
    }

    public void editar() {
        pessoaBusiness.salvar(pessoaSelecionada);
        // Atualize sua lista de pessoas, se necessário
        pessoas = pessoaBusiness.buscarTodos();
    }

    public void excluir(PessoaDTO pessoaDTO) {
        pessoaBusiness.excluir(pessoaDTO);
        pessoas = pessoaBusiness.buscarTodos();
    }
}