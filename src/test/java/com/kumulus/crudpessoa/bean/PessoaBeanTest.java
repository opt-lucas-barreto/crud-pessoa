package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.PessoaDTO;
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

import static org.mockito.Mockito.*;

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
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pessoaDTO.setDataNascimento(date);
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
        pessoaDTO.setNome("Nome Editado");
        pessoaBean.setPessoaSelecionada(pessoaDTO);
        pessoaBean.salvarOuEditar();
        verify(pessoaBusiness, times(1)).editar(pessoaDTO);
    }

    @Test
    public void deveExcluirPessoaComSucesso() {
        pessoaBean.excluir();
        verify(pessoaBusiness, times(1)).excluir(pessoaDTO);
    }
}