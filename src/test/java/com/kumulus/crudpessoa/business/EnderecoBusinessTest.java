package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.EnderecoDAO;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnderecoBusinessTest {

    @Mock
    private EnderecoDAO dao;

    @InjectMocks
    private EnderecoBusiness enderecoBusiness;

    private EnderecoDTO enderecoDTO;
    private Endereco endereco;
    private List<Endereco> enderecos;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1);
        enderecoDTO.setLogradouro("Rua teste");
        enderecoDTO.setNumero(100);
        enderecoDTO.setBairro("Bairro teste");
        enderecoDTO.setCidade("Cidade teste");
        enderecoDTO.setEstado("Estado teste");
        enderecoDTO.setCep("12345678");

        endereco = new Endereco();
        endereco.setId(1);
        endereco.setLogradouro("Rua teste");
        endereco.setNumero(100);
        endereco.setBairro("Bairro teste");
        endereco.setCidade("Cidade teste");
        endereco.setEstado("Estado teste");
        endereco.setCep("12345678");

        enderecos = new ArrayList<>();
        enderecos.add(endereco);
    }

    @Test
    public void deveSalvarEnderecoComSucesso() {
        enderecoBusiness.salvar(enderecoDTO);
        verify(dao, times(1)).salvar(any(Endereco.class));
    }

    @Test
    public void deveEditarEnderecoComSucesso() {
        enderecoBusiness.editar(enderecoDTO);
        verify(dao, times(1)).editar(any(Endereco.class));
    }

    @Test
    public void deveExcluirEnderecoComSucesso() {
        enderecoBusiness.excluir(enderecoDTO);
        verify(dao, times(1)).excluir(any(Endereco.class));
    }

    @Test
    public void deveBuscarEnderecoPorIdComSucesso() {
        when(dao.buscarPorId(1)).thenReturn(endereco);
        EnderecoDTO result = enderecoBusiness.buscarPorId(1);
        assertEquals(enderecoDTO, result);
    }

    @Test
    public void deveBuscarEnderecosPorPessoaIdComSucesso() {
        when(dao.buscarPorPessoaId(1)).thenReturn(enderecos);
        List<EnderecoDTO> result = enderecoBusiness.buscarPorPessoaId(1);
        assertEquals(1, result.size());
        assertEquals(enderecoDTO, result.get(0));
    }

    @Test
    public void deveBuscarTodosEnderecosComSucesso() {
        when(dao.buscarTodos()).thenReturn(enderecos);
        List<EnderecoDTO> result = enderecoBusiness.buscarTodos();
        assertEquals(1, result.size());
        assertEquals(enderecoDTO, result.get(0));
    }

}
