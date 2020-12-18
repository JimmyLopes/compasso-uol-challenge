package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.configuration.ModelMapperConfig;
import br.com.compasso.uol.backend.dtos.ClienteAlterarNomeDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.exceptions.BusinessException;
import br.com.compasso.uol.backend.exceptions.ResourceNotFoundException;
import br.com.compasso.uol.backend.models.Cliente;
import br.com.compasso.uol.backend.repositorys.ClienteRepository;
import br.com.compasso.uol.backend.services.implementation.ClienteServiceImpl;
import br.com.compasso.uol.backend.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ClienteServiceTest {
    private static final String MOCK_FOLDER = "mocks";
    private static final String FILE_NAME_CLIENTE = "cliente.json";
    private static final String FILE_NAME_NOVO_CLIENTE = "novoCliente.json";

    @Mock
    private ClienteRepository clienteRepository;

    private final ModelMapper mapper = new ModelMapperConfig().modelMapper();

    private ClienteServiceImpl clienteService() {
        return new ClienteServiceImpl(clienteRepository, mapper);
    }

    static Cliente getMockCliente() {
        return TestUtils.getMock(MOCK_FOLDER, FILE_NAME_CLIENTE, Cliente.class);
    }

    static NovoClienteDto getMockNovoCliente() {
        return TestUtils.getMock(MOCK_FOLDER, FILE_NAME_NOVO_CLIENTE, NovoClienteDto.class);
    }

    @Test
    void testarSalvarCliente() {
        Cliente cliente = getMockCliente();
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        Cliente clienteSalvo = clienteService().salvarCliente(getMockNovoCliente());
        assertEquals(clienteSalvo.getId(), cliente.getId());
        assertEquals(clienteSalvo.getNomeCompleto(), cliente.getNomeCompleto());
        assertEquals(clienteSalvo.getDataNascimento(), cliente.getDataNascimento());
        assertEquals(clienteSalvo.geteSexo(), cliente.geteSexo());
        assertEquals(clienteSalvo.getIdade(), cliente.getIdade());
    }

    @Test
    void testarBuscarClientePeloNome() {
        Cliente cliente = getMockCliente();
        when(clienteRepository.findByNomeCompleto(Mockito.anyString())).thenReturn(Optional.of(cliente));
        Cliente clienteProcurado = clienteService().buscarClientePorNome(cliente.getNomeCompleto());
        assertEquals(clienteProcurado.getId(), cliente.getId());
        assertEquals(clienteProcurado.getNomeCompleto(), cliente.getNomeCompleto());
        assertEquals(clienteProcurado.getDataNascimento(), cliente.getDataNascimento());
        assertEquals(clienteProcurado.geteSexo(), cliente.geteSexo());
        assertEquals(clienteProcurado.getIdade(), cliente.getIdade());
    }

    @Test
    void testarClienteNaoEncontradoPeloNome() {
        String nomeCliente = getMockCliente().getNomeCompleto();
        ClienteServiceImpl clienteService = clienteService();
        when(clienteRepository.findByNomeCompleto(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> clienteService.buscarClientePorNome(nomeCliente));
    }

    @Test
    void testarBuscarClientePeloId(){
        Cliente cliente = getMockCliente();
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));
        Cliente clienteProcurado = clienteService().buscarPorId(cliente.getId());
        assertEquals(clienteProcurado.getId(), cliente.getId());
        assertEquals(clienteProcurado.getNomeCompleto(), cliente.getNomeCompleto());
        assertEquals(clienteProcurado.getDataNascimento(), cliente.getDataNascimento());
        assertEquals(clienteProcurado.geteSexo(), cliente.geteSexo());
        assertEquals(clienteProcurado.getIdade(), cliente.getIdade());
    }

    @Test
    void testarDeletarCliente(){
        Cliente cliente = getMockCliente();
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));
        clienteService().deletarCliente(Mockito.anyLong());
        verify(clienteRepository, Mockito.times(1)).delete(cliente);
    }

    @Test
    void testarAlterarNomeCliente(){
        Cliente cliente = getMockCliente();
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        ClienteAlterarNomeDto clienteAlterarNomeDto = new ClienteAlterarNomeDto();
        clienteAlterarNomeDto.setNomeCompleto("Nome Alterado");
        clienteService().alterarNomeCliente(cliente.getId(), clienteAlterarNomeDto);
        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);
        Mockito.verify(clienteRepository, Mockito.times(1)).findById(cliente.getId());
    }

    @Test
    void testarAlterarNomeClienteIgual(){
        Cliente cliente = getMockCliente();
        long clienteId = cliente.getId();
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        ClienteAlterarNomeDto clienteAlterarNomeDto = new ClienteAlterarNomeDto();
        clienteAlterarNomeDto.setNomeCompleto(cliente.getNomeCompleto());
        ClienteServiceImpl clienteService = clienteService();
        assertThrows(BusinessException.class, () -> clienteService.alterarNomeCliente(clienteId, clienteAlterarNomeDto));
    }
}
