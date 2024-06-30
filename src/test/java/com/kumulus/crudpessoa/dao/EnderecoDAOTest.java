package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.model.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnderecoDAOTest {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Endereco> query;


    @InjectMocks
    private EnderecoDAO dao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarEnderecoComSucesso() {
        Endereco endereco = new Endereco();
        dao.salvar(endereco);
        verify(em, times(1)).persist(endereco);
    }

    @Test
    public void deveEditarEnderecoComSucesso() {
        Endereco endereco = new Endereco();
        dao.editar(endereco);
        verify(em, times(1)).merge(endereco);
    }

    @Test
    public void deveBuscarEnderecoPorIdComSucesso() {
        Integer id = 1;
        Endereco endereco = new Endereco();
        when(em.find(Endereco.class, id)).thenReturn(endereco);
        Endereco result = dao.buscarPorId(id);
        assertEquals(endereco, result);
    }

    @Test
    public void deveBuscarEnderecosPorPessoaIdComSucesso() {
        Integer pessoaId = 1;
        List<Endereco> expectedEnderecos = new ArrayList<>();
        Endereco endereco = new Endereco();
        expectedEnderecos.add(endereco);
        when(em.createQuery("SELECT e FROM Endereco e WHERE e.idPessoa = :pessoaId", Endereco.class)).thenReturn(query);
        when(query.setParameter("pessoaId", pessoaId)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedEnderecos);
        List<Endereco> result = dao.buscarPorPessoaId(pessoaId);
        assertNotNull(result, "A lista de endereços não deve estar vazia");
        assertEquals(expectedEnderecos.size(), result.size(), "O tamanho da lista de endereços deve corresponder");
        assertEquals(expectedEnderecos.get(0), result.get(0), "Os endereços retornados devem corresponder aos esperados");
        verify(em, times(1)).createQuery("SELECT e FROM Endereco e WHERE e.idPessoa = :pessoaId", Endereco.class);
        verify(query, times(1)).setParameter("pessoaId", pessoaId);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void deveBuscarTodosEnderecosComSucesso() {
        List<Endereco> expectedEnderecos = new ArrayList<>();
        expectedEnderecos.add(new Endereco());
        when(em.createQuery(any(String.class), eq(Endereco.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedEnderecos);
        List<Endereco> result = dao.buscarTodos();
        assertNotNull(result);
        assertEquals(expectedEnderecos.size(), result.size());
        verify(em, times(1)).createQuery(any(String.class), eq(Endereco.class));
    }

    @Test
    public void deveExcluirEnderecoPorPessoaIdComErro() {
        Integer pessoaId = 1;
        when(em.find(Endereco.class, pessoaId)).thenReturn(null);
        dao.excluirPorPessoaId(pessoaId);
        verify(em, times(0)).remove(any(Endereco.class));
    }

    @Test
    public void deveBuscarEnderecoPorIdComErro() {
        Integer id = 1;
        when(em.find(Endereco.class, id)).thenReturn(null);
        Endereco result = dao.buscarPorId(id);
        assertNull(result);
    }

    @Test
    public void deveBuscarEnderecosPorPessoaIdComErro() {
        Integer pessoaId = 1;
        List<Endereco> enderecosVazios = new ArrayList<>();
        when(em.createQuery("SELECT e FROM Endereco e WHERE e.idPessoa = :pessoaId", Endereco.class)).thenReturn(query);
        when(query.setParameter("pessoaId", pessoaId)).thenReturn(query);
        when(query.getResultList()).thenReturn(enderecosVazios);
        List<Endereco> result = dao.buscarPorPessoaId(pessoaId);
        assertTrue(result.isEmpty(), "A lista de endereços deve estar vazia");
        verify(em, times(1)).createQuery("SELECT e FROM Endereco e WHERE e.idPessoa = :pessoaId", Endereco.class);
        verify(query, times(1)).setParameter("pessoaId", pessoaId);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void deveBuscarTodosEnderecosComErro() {
        List<Endereco> enderecosVazios = new ArrayList<>();
        when(em.createQuery("SELECT e FROM Endereco e", Endereco.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(enderecosVazios);
        List<Endereco> result = dao.buscarTodos();
        assertTrue(result.isEmpty(), "A lista de endereços deve estar vazia");
        verify(em, times(1)).createQuery("SELECT e FROM Endereco e", Endereco.class);
        verify(query, times(1)).getResultList();
    }

}
