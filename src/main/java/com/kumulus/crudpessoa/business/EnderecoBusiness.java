package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.EnderecoDAO;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.exception.BusinessException;
import com.kumulus.crudpessoa.model.Endereco;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.postgresql.util.PSQLException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EnderecoBusiness implements Serializable {

    @EJB
    private EnderecoDAO dao;

    public void salvar(EnderecoDTO endereco) {
        try {
            dao.salvar(endereco.toEndereco());
        }catch (Exception e) {
            trataExcecaoPersistence(e);
        }
    }

    public void editar(EnderecoDTO endereco) {
        try{
            dao.editar(endereco.toEndereco());
        }catch (Exception e) {
            trataExcecaoPersistence(e);
        }
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

    private void trataExcecaoPersistence(Exception e) {
        Throwable cause = e.getCause();
        String reason = null;
        while (cause != null && !(cause instanceof PSQLException)) {
            cause = cause.getCause();
            reason = cause!=null ? cause.getMessage() : reason;
        }
        if (reason != null && reason.contains("uc_endereco")) {
            throw new BusinessException("Já existe  o endereço cadastrado para a pessoa e não é possível duplicar");
        }
        throw new BusinessException("Erro ao salvar endereço: " + e.getMessage());
    }

    public EnderecoDTO buscarEndereco(EnderecoDTO enderecoSelecionado) {
        return dao.buscarEndereco(enderecoSelecionado).toEnderecoDTO();
    }
}
