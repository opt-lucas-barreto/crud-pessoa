package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.PessoaDAO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.exception.BusinessException;
import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceException;
import org.postgresql.util.PSQLException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PessoaBusiness implements Serializable {

    @EJB
    private PessoaDAO pessoaDAO;

    @EJB
    private EnderecoBusiness enderecoBusiness;

    public void salvar(PessoaDTO pessoaDTO) throws BusinessException {
        try{
            pessoaDAO.salvar(pessoaDTO.toPessoa());
        } catch (Exception e) {
            trataExcecaoPersistence(e, pessoaDTO);
        }

    }

    public void editar(PessoaDTO pessoaDTO) throws BusinessException {
        pessoaDAO.editar(pessoaDTO.toPessoa());
    }

    public List<PessoaDTO> buscarTodos() {
        return pessoaDAO.buscarTodos().stream().map(Pessoa::toPessoaDTO).collect(Collectors.toList());
    }

    public void excluir(PessoaDTO pessoaDTO) {
        enderecoBusiness.excluirPorPessoaId(pessoaDTO.getId());
        pessoaDAO.excluir(pessoaDTO.toPessoa());
    }

    public PessoaDTO buscarPessoa(PessoaDTO pessoaSelecionada) {
        return pessoaDAO.buscarPessoa(pessoaSelecionada).toPessoaDTO();
    }

    private void trataExcecaoPersistence(Exception e, PessoaDTO pessoaDTO) {
        Throwable cause = e.getCause();
        String reason = null;
        while (cause != null && !(cause instanceof PSQLException)) {
            cause = cause.getCause();
            reason = cause!=null ? cause.getMessage() : reason;
        }
        if (reason != null && reason.contains("uc_pessoa")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = sdf.format(pessoaDTO.getDataNascimento());
            throw new BusinessException("Já existe " + pessoaDTO.getNome() + ", " + dataFormatada + ", " + pessoaDTO.getSexo() + " e não é possível duplicar");
        }
        throw new BusinessException("Erro ao salvar pessoa: " + e.getMessage());
    }
}

