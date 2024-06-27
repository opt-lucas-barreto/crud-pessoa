package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Named
@RequestScoped
public class PessoaBean {

    @Inject
    private PessoaBusiness pessoaBusiness; // Assuming you have a service to fetch people

    @Getter @Setter
    private List<PessoaDTO> pessoas;

    @PostConstruct
    public void init() {
        pessoas = pessoaBusiness.buscarTodos();
    }

}