package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.EnderecoBusiness;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnderecoBeanTest {

    @Mock
    private EnderecoBusiness enderecoBusiness;

    @Mock
    private PessoaBean pessoaBean;

    @InjectMocks
    private EnderecoBean enderecoBean;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveChamarNovoComSucesso() {
        Pessoa pessoaSelecionada = new Pessoa();
        pessoaSelecionada.setId(1);
        when(pessoaBean.getPessoaSelecionada()).thenReturn(pessoaSelecionada.toPessoaDTO());
        enderecoBean.novo();
        assertNotNull(enderecoBean.getEnderecoSelecionado());
        assertEquals(1, enderecoBean.getEnderecoSelecionado().getIdPessoa());
    }

    @Test
    public void deveSalvarEnderecoComSucesso() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setLogradouro("Rua das Flores");
        enderecoDTO.setNumero(123);
        enderecoDTO.setBairro("Jardim das Acácias");
        enderecoDTO.setCidade("Cidade das Árvores");
        enderecoDTO.setEstado("ES");
        enderecoDTO.setCep("12345-678");

        enderecoBean.salvar(enderecoDTO);
        verify(enderecoBusiness, times(1)).salvar(enderecoDTO);
    }

    @Test
    public void deveBuscarEnderecosPorPessoaIdComSucesso() {
        List<EnderecoDTO> enderecosEsperados = new ArrayList<>();
        when(enderecoBusiness.buscarPorPessoaId(1)).thenReturn(enderecosEsperados);

        List<EnderecoDTO> result = enderecoBean.getEnderecosPorPessoaId(1);

        assertSame(enderecosEsperados, result);
        verify(enderecoBusiness, times(1)).buscarPorPessoaId(1);
    }

    @Test
    public void deveExcluirEnderecoComSucesso() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoBean.setEnderecoSelecionado(enderecoDTO);

        doNothing().when(enderecoBusiness).excluir(any(EnderecoDTO.class));

        enderecoBean.excluir();

        verify(enderecoBusiness, times(1)).excluir(enderecoDTO);
    }

}
