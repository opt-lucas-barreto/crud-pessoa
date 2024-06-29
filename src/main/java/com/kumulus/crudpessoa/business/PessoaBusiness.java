package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.PessoaDAO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PessoaBusiness implements Serializable {

    @EJB
    private PessoaDAO pessoaDAO;

    @Inject
    private EnderecoBusiness enderecoBusiness;

    public void salvar(PessoaDTO pessoaDTO) {
        pessoaDAO.salvar(pessoaDTO.toPessoa());
    }

    public void editar(PessoaDTO pessoaDTO) {
        pessoaDAO.editar(pessoaDTO.toPessoa());
    }

    public List<PessoaDTO> buscarTodos() {
        return pessoaDAO.buscarTodos().stream().map(Pessoa::toPessoaDTO).collect(Collectors.toList());
    }

    public void excluir(PessoaDTO pessoaDTO) {
        enderecoBusiness.excluirPorPessoaId(pessoaDTO.getId());
        pessoaDAO.excluir(pessoaDTO.toPessoa());
    }
}
