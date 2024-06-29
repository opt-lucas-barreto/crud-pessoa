package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.EnderecoBusiness;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EnderecoBean implements Serializable {

    @EJB
    private EnderecoBusiness enderecoBusiness;

    @Inject
    private PessoaBean pessoaBean;

    @Getter @Setter
    private List<EnderecoDTO> enderecos;

    @PostConstruct
    public void init() {

    }

    public List<EnderecoDTO> getEnderecosList() {
        return this.enderecoBusiness.buscarTodos();
    }

    public void salvar(EnderecoDTO dto) {
        enderecoBusiness.salvar(dto);
    }

    public List<EnderecoDTO> getEnderecosPorPessoaId(Integer pessoaId) {
        return enderecoBusiness.buscarPorPessoaId(pessoaId);
    }

    public List<EnderecoDTO> buscarEnderecosPorPessoaSelecionada() {
        return this.getEnderecosPorPessoaId(pessoaBean.getPessoaSelecionada().getId());
    }
}
