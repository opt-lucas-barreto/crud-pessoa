package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.EnderecoDAO;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.model.Endereco;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EnderecoBusiness implements Serializable {

    @EJB
    private EnderecoDAO dao;

    public void salvar(EnderecoDTO endereco) {
        dao.salvar(endereco.toEndereco());
    }

    public void editar(EnderecoDTO endereco) {
        dao.editar(endereco.toEndereco());
    }

    public void excluir(EnderecoDTO endereco) {
        dao.excluir(endereco.toEndereco());
    }

    public void excluirPorPessoaId(Integer pessoaId) {
        dao.excluirPorPessoaId(pessoaId);
    }

    public EnderecoDTO buscarPorId(Integer id) {
        return dao.buscarPorId(id).toEnderecoDTO();
    }

    public List<EnderecoDTO> buscarPorPessoaId(Integer pessoaId) {
        return dao.buscarPorPessoaId(pessoaId).stream().map(Endereco::toEnderecoDTO).collect(Collectors.toList());
    }

    public List<EnderecoDTO> buscarTodos() {
        return dao.buscarTodos().stream().map(Endereco::toEnderecoDTO).collect(Collectors.toList());
    }
}
