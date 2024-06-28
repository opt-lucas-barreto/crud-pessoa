package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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

    public void editar() {
        pessoaBusiness.editar(pessoaSelecionada);
        // Atualize sua lista de pessoas, se necessário
        pessoas = pessoaBusiness.buscarTodos();
    }

    public void excluir() {
        try {
            if(pessoaSelecionada == null || pessoaSelecionada.getId() == null) {
                throw new FacesException("Selecione uma pessoa");
            }
            pessoaBusiness.excluir(pessoaSelecionada);
            pessoas = pessoaBusiness.buscarTodos();
        }catch(Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível excluir a pessoa: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}