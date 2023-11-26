package com.test.avaliable.api.v1;

import com.test.avaliable.business.service.ContatoService;
import com.test.avaliable.fixture.ContatoDTOFixture;
import com.test.avaliable.fixture.ContatoRequestDTOFixture;
import com.test.avaliable.fixture.ContatoResponseDTOFixture;
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
public class ContatoRestTest {

    @InjectMocks
    ContatoRest contatoRest;

    @Mock
    private ContatoService contatoService;

    @BeforeEach
    void setup(){
        MockMvcBuilders.standaloneSetup(contatoRest).alwaysDo(print()).build();
    }

    @Test
    void adicionarContato(){
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var contatoResponseDTO = ContatoResponseDTOFixture.build();
        var uriBuilder = UriComponentsBuilder.newInstance();

        when(contatoService.adicionarContato(contatoRequestDTO)).thenReturn(contatoResponseDTO);

        var retorno = contatoRest.adicionarContato(contatoRequestDTO, uriBuilder);

        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
        assertEquals(contatoResponseDTO.getId(), Objects.requireNonNull(retorno.getBody()).getId());
    }

    @Test
    void buscarContato(){
        var id = UUID.randomUUID();
        var contatoDTO = ContatoDTOFixture.build();

        when(contatoService.buscarContato(String.valueOf(id))).thenReturn(contatoDTO);

        var retorno = contatoRest.buscarContato(String.valueOf(id));

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(contatoDTO.getContato(), Objects.requireNonNull(retorno.getBody()).getContato());
        assertEquals(contatoDTO.getNome(), Objects.requireNonNull(retorno.getBody()).getNome());
        assertEquals(contatoDTO.getNomeProfissional(), Objects.requireNonNull(retorno.getBody()).getNomeProfissional());
        assertEquals(contatoDTO.getDataCriacao(), Objects.requireNonNull(retorno.getBody()).getDataCriacao());
    }

    @Test
    void alterarContato(){
        var id = UUID.randomUUID();
        var contatoRequestDTO = ContatoRequestDTOFixture.build();

        doNothing().when(contatoService).alterarContato(String.valueOf(id), contatoRequestDTO);

        var retorno = contatoRest.alterarContato(String.valueOf(id), contatoRequestDTO);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void excluirContato(){
        var id = UUID.randomUUID();

        doNothing().when(contatoService).deletarContato(String.valueOf(id));

        var retorno = contatoRest.excluirContato(String.valueOf(id));

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void buscarListaContato(){
        var filtro = "euv";
        var contatoDTO = ContatoDTOFixture.build();
        var paginacao = PageRequest.of(0, 10);

        when(contatoService.buscarListaContato(filtro, List.of(), paginacao)).thenReturn(List.of(contatoDTO));

        var retorno = contatoRest.buscarListaContato(filtro, List.of(), paginacao);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(contatoDTO.getContato(), Objects.requireNonNull(retorno.getBody()).get(0).getContato());
        assertEquals(contatoDTO.getNome(), Objects.requireNonNull(retorno.getBody()).get(0).getNome());
        assertEquals(contatoDTO.getNomeProfissional(), Objects.requireNonNull(retorno.getBody()).get(0).getNomeProfissional());
        assertEquals(contatoDTO.getDataCriacao(), Objects.requireNonNull(retorno.getBody()).get(0).getDataCriacao());
    }
}
