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
        enderecos = this.buscarEnderecosPorPessoaSelecionada();
    }

    public void novo() {
        this.enderecoSelecionado = new EnderecoDTO();
        this.enderecoSelecionado.setIdPessoa(pessoaBean.getPessoaSelecionada().getId());
    }

    public List<EnderecoDTO> getEnderecosList() {
        return this.enderecoBusiness.buscarTodos();
    }

    public void salvar(EnderecoDTO dto) {
        try{
            if(validarEnderecoDTO(dto)) {
                enderecoBusiness.salvar(dto);
            }
        }catch(Exception e){
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao excluir endereço", e.getCause().getMessage());
        }
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
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao excluir endereço", e.getCause().getMessage());
        }
    }

    public void editar() {
        try {
            if(validarEnderecoDTO(enderecoSelecionado)) {
                enderecoBusiness.editar(enderecoSelecionado);
            }
        }catch(Exception e){
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar endereço", e.getCause().getMessage());
        }
    }

    public boolean validarEnderecoDTO(EnderecoDTO endereco) {
        if (endereco == null) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Endereço não pode ser nulo");
            return false;
        }
        if (endereco.getEstado() == null || endereco.getEstado().length() != 2) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Estado inválido");
            return false;
        }
        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Cidade não pode ser vazia");
            return false;
        }
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Logradouro não pode ser vazio");
            return false;
        }
        if (endereco.getNumero() <= 0) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Número inválido");
            return false;
        }
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Bairro não pode ser vazio");
            return false;
        }
        if (endereco.getCep() == null || !endereco.getCep().replaceAll("[^0-9]", "").matches("\\d{8}")) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro de validação", "CEP inválido");
            return false;
        }
        // Se todas as validações passarem
        return true;
    }
}
