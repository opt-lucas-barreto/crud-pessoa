package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.EnderecoBusiness;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.utils.Mensagens;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
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
    PessoaBean pessoaBean;

    @Getter @Setter
    private EnderecoDTO enderecoSelecionado;

    @Getter @Setter
    private List<EnderecoDTO> enderecos;

    @PostConstruct
    public void init() {

    }

    public void novo() {
        this.enderecoSelecionado = new EnderecoDTO();
        this.enderecoSelecionado.setIdPessoa(pessoaBean.getPessoaSelecionada().getId());
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

    public void excluir() {
        try {
            this.enderecoBusiness.excluir(enderecoSelecionado);
        }catch(Exception e){
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao excluir endereço", e.getMessage());
        }
    }

    public void editar() {
        enderecoBusiness.editar(enderecoSelecionado);
    }
}
