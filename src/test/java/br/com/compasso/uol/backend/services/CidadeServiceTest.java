package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.configuration.ModelMapperConfig;
import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.exceptions.ResourceNotFoundException;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.repositorys.CidadeRepository;
import br.com.compasso.uol.backend.services.implementation.CidadeServiceImpl;
import br.com.compasso.uol.backend.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CidadeServiceTest {

    private static final String MOCK_FOLDER = "mocks";
    private static final String FILE_NAME_CIDADE = "cidade.json";
    private static final String FILE_NAME_NOVA_CIDADE = "novaCidade.json";

    @Mock
    private CidadeRepository cidadeRepository;

    private final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    private CidadeServiceImpl cidadeService() {
        return new CidadeServiceImpl(cidadeRepository, mapper);
    }

    static Cidade getMockCidade() {
        return TestUtils.getMock(MOCK_FOLDER, FILE_NAME_CIDADE, Cidade.class);
    }

    static NovaCidadeDto getMockNovaCidade() {
        return TestUtils.getMock(MOCK_FOLDER, FILE_NAME_NOVA_CIDADE, NovaCidadeDto.class);
    }

    /**
     * Testa se uma cidade válida vai ser salva pelo serviço
     */
    @Test
    void testarSalvarCidade() {
        Cidade cidade = getMockCidade();
        when(cidadeRepository.save(Mockito.any())).thenReturn(cidade);
        Cidade cidadeSalva = cidadeService().salvarCidade(getMockNovaCidade());
        assertEquals(cidadeSalva.getId(), cidade.getId());
        assertEquals(cidadeSalva.getNome(), cidade.getNome());
        assertEquals(cidadeSalva.geteEstado(), cidade.geteEstado());
    }

    /**
     * Teste se uma cidade vai ser encontrada quando um nome válido é passado
     */
    @Test
    void testarBuscarCidadePeloNome() {
        Cidade cidade = getMockCidade();
        when(cidadeRepository.findByNome(Mockito.anyString())).thenReturn(Optional.of(cidade));
        Cidade cidadeProcurada = cidadeService().buscarCidadePeloNome(cidade.getNome());
        assertEquals(cidadeProcurada.getId(), cidade.getId());
        assertEquals(cidadeProcurada.getNome(), cidade.getNome());
        assertEquals(cidadeProcurada.geteEstado(), cidade.geteEstado());
    }

    /**
     * Testa se a aplicação vai lançar uma exceção caso não encontre uma cidade com o nome passado
     */
    @Test
    void testarBuscarCidadeNaoEncontradoPeloNome() {
        String nomeCidade = getMockCidade().getNome();
        CidadeServiceImpl cidadeService = cidadeService();
        when(cidadeRepository.findByNome(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> cidadeService.buscarCidadePeloNome(nomeCidade));
    }

    /**
     * Dado um Estado, testa se a aplicação vai buscar a lista de cidades daquele estado
     */
    @Test
    void testarBuscarCidadesPorEstado() {
        when(cidadeRepository.findAllByEstado(Mockito.any())).thenReturn(Collections.singletonList(getMockCidade()));
        List<Cidade> cidades = cidadeService().buscarCidadesPorEstado(EstadoEnum.SP);
        assertEquals(1, cidades.size());
    }

    /**
     * Testa se a aplicação vai lançar uma exceção caso não encontre nenhuma cidade para um Estado passado por parâmetro
     */
    @Test
    void testarBuscarCidadesSemSucessoPorEstado() {
        EstadoEnum estadoEnum = EstadoEnum.SP;
        CidadeServiceImpl cidadeService = cidadeService();
        when(cidadeRepository.findAllByEstado(Mockito.any())).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> cidadeService.buscarCidadesPorEstado(estadoEnum));
    }
}
