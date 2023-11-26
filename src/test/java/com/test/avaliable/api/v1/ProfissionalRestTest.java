package com.test.avaliable.api.v1;

import com.test.avaliable.business.service.ProfissionalService;
import com.test.avaliable.fixture.ProfissionalDTOFixture;
import com.test.avaliable.fixture.ProfissionalRequestDTOFixture;
import com.test.avaliable.fixture.ProfissionalResponseDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class ProfissionalRestTest {

    @InjectMocks
    ProfissionalRest profissionalRest;

    @Mock
    private ProfissionalService profissionalService;

    @BeforeEach
    void setup(){
        MockMvcBuilders.standaloneSetup(profissionalRest).alwaysDo(print()).build();
    }

    @Test
    void adicionarProfissional(){
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();
        var profissionalResponseDTO = ProfissionalResponseDTOFixture.build();
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(profissionalService.adicionarProfissional(profissionalRequestDTO)).thenReturn(profissionalResponseDTO);

        var retorno = profissionalRest.adicionarProfissional(profissionalRequestDTO, uriBuilder);

        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
        assertEquals(profissionalResponseDTO.getId(), Objects.requireNonNull(retorno.getBody()).getId());
    }

    @Test
    void buscarProfissional(){
        var id = UUID.randomUUID();
        var profissionalDTO = ProfissionalDTOFixture.build();

        when(profissionalService.buscarProfissional(String.valueOf(id))).thenReturn(profissionalDTO);

        var retorno = profissionalRest.buscarProfissional(String.valueOf(id));

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(profissionalDTO.getAtivo(), Objects.requireNonNull(retorno.getBody()).getAtivo());
        assertEquals(profissionalDTO.getCargo(), Objects.requireNonNull(retorno.getBody()).getCargo());
        assertEquals(profissionalDTO.getNome(), Objects.requireNonNull(retorno.getBody()).getNome());
        assertEquals(profissionalDTO.getDataNascimento(), Objects.requireNonNull(retorno.getBody()).getDataNascimento());
        assertEquals(profissionalDTO.getDataCriacao(), Objects.requireNonNull(retorno.getBody()).getDataCriacao());
    }

    @Test
    void alterarProfissional(){
        var id = UUID.randomUUID();
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();

        doNothing().when(profissionalService).alterarProfissional(String.valueOf(id), profissionalRequestDTO);

        var retorno = profissionalRest.alterarProfissional(String.valueOf(id), profissionalRequestDTO);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void excluirProfissional(){
        var id = UUID.randomUUID();

        doNothing().when(profissionalService).deletarProfissional(String.valueOf(id));

        var retorno = profissionalRest.excluirProfissional(String.valueOf(id));

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void buscarListaProfissional(){
        var filtro = "euv";
        var profissionalDTO = ProfissionalDTOFixture.build();
        var paginacao = PageRequest.of(0, 10);

        when(profissionalService.buscarListaProfissional(filtro, List.of(), paginacao)).thenReturn(List.of(profissionalDTO));

        var retorno = profissionalRest.buscarListaProfissional(filtro, List.of(), paginacao);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(profissionalDTO.getAtivo(), Objects.requireNonNull(retorno.getBody()).get(0).getAtivo());
        assertEquals(profissionalDTO.getCargo(), Objects.requireNonNull(retorno.getBody()).get(0).getCargo());
        assertEquals(profissionalDTO.getNome(), Objects.requireNonNull(retorno.getBody()).get(0).getNome());
        assertEquals(profissionalDTO.getDataNascimento(), Objects.requireNonNull(retorno.getBody()).get(0).getDataNascimento());
        assertEquals(profissionalDTO.getDataCriacao(), Objects.requireNonNull(retorno.getBody()).get(0).getDataCriacao());
    }
}
