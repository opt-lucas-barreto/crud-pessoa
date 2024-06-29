package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.EnderecoBusiness;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EnderecoBean implements Serializable {

    @Inject
    private EnderecoBusiness enderecoBusiness;

    public List<EnderecoDTO> getEnderecos() {
        return this.enderecoBusiness.buscarTodos();
    }

    public void salvar(EnderecoDTO dto) {
        enderecoBusiness.salvar(dto);
    }

    public List<EnderecoDTO> getEnderecosPorPessoaId(Integer pessoaId) {
        return enderecoBusiness.buscarPorPessoaId(pessoaId);
    }
}
