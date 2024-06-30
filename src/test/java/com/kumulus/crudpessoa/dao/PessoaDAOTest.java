package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoaDAOTest {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Pessoa> query;

    @InjectMocks
    private PessoaDAO pessoaDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarPessoaComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoaDAO.salvar(pessoa);
        verify(em, times(1)).persist(pessoa);
    }

    @Test
    public void deveExcluirPessoaComSucesso() {
        Pessoa pessoa = new Pessoa();
        when(em.contains(pessoa)).thenReturn(true);
        pessoaDAO.excluir(pessoa);
        verify(em, times(1)).remove(pessoa);
    }

    @Test
    public void deveEditarPessoaComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoaDAO.editar(pessoa);
        verify(em, times(1)).merge(pessoa);
    }

    @Test
    public void deveBuscarTodosComSucesso() {
        List<Pessoa> expectedPessoas = new ArrayList<>();
        when(em.createQuery("SELECT DISTINCT p FROM Pessoa p LEFT JOIN FETCH p.enderecos", Pessoa.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedPessoas);
        pessoaDAO.buscarTodos();
        verify(query, times(1)).getResultList();
    }

    @Test
    public void deveBuscarPessoaComSucesso() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Teste");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoaDTO.setSexo("M");

        when(em.createQuery(anyString(), eq(Pessoa.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(new Pessoa());

        pessoaDAO.buscarPessoa(pessoaDTO);

        verify(query, times(1)).getSingleResult();
    }
}
