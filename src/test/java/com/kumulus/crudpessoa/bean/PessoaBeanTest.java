package com.kumulus.crudpessoa.bean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kumulus.crudpessoa.bean.PessoaBean;
import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.model.Pessoa;
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

public class PessoaBeanTest {

    @Mock
    private PessoaBusiness pessoaBusiness;

    @InjectMocks
    private PessoaBean pessoaBean;

    private PessoaDTO pessoaDTO;
    private List<PessoaDTO> listaPessoas;
    private final Integer pessoaId = 1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoaId);
        pessoaDTO.setNome("Teste Pessoa");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoaDTO.setEnderecos(new ArrayList<>());
        pessoaDTO.setSexo("M");
        pessoaBean.setPessoaSelecionada(pessoaDTO);

        listaPessoas = new ArrayList<>();
        listaPessoas.add(pessoaDTO);
    }

    @Test
    public void deveSalvarPessoaComSucesso() {
        pessoaDTO.setId(null);
        when(pessoaBusiness.buscarPessoa(any(PessoaDTO.class))).thenReturn(pessoaDTO);
        pessoaBean.salvarOuEditar();
        verify(pessoaBusiness, times(1)).salvar(pessoaDTO);
    }

    @Test
    public void deveEditarPessoaComSucesso() {
        // Configura pessoaDTO para simular uma edição
        pessoaDTO.setNome("Nome Editado");
        pessoaBean.setPessoaSelecionada(pessoaDTO); // Garante que pessoaBean está utilizando o DTO editado

        pessoaBean.salvarOuEditar(); // Supondo que o método a ser testado é salvarOuEditar

        verify(pessoaBusiness, times(1)).editar(pessoaDTO);
    }

    @Test
    public void deveExcluirPessoaComSucesso() {
        // Utiliza pessoaDTO configurado no setUp
        pessoaBean.excluir(); // Assume que o método excluir() não precisa de argumentos

        verify(pessoaBusiness, times(1)).excluir(pessoaDTO);
    }

    // Considerar a criação de mais testes para cobrir todos os métodos públicos de PessoaBean
}