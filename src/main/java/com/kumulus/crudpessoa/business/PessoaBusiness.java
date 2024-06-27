package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.PessoaDAO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.mapper.PessoaMapper;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class PessoaBusiness {

    @EJB
    private PessoaDAO pessoaDAO;

    private PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    public void salvar(PessoaDTO pessoaDTO) {
        pessoaDAO.salvar(pessoaMapper.pessoaDTOToPessoa(pessoaDTO));
    }

    public List<PessoaDTO> buscarTodos() {
        return pessoaMapper.pessoaDTOToPessoaList(pessoaDAO.buscarTodos());
    }
}
