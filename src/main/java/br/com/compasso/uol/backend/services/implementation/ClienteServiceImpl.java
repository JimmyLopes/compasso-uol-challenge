package br.com.compasso.uol.backend.services.implementation;

import br.com.compasso.uol.backend.dtos.ClienteAlterarNomeDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.exceptions.BusinessException;
import br.com.compasso.uol.backend.exceptions.ResourceNotFoundException;
import br.com.compasso.uol.backend.models.Cliente;
import br.com.compasso.uol.backend.repositorys.ClienteRepository;
import br.com.compasso.uol.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static br.com.compasso.uol.backend.utils.StringUtils.buscarMensagemDeValidacao;

@Service
@Validated
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    private Cliente buscarClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException(buscarMensagemDeValidacao("cliente.id.nao.encontrado", idCliente)));
    }

    @Override
    public Cliente salvarCliente(@Valid NovoClienteDto novoClienteDto) {
        Cliente cliente = modelMapper.map(novoClienteDto, Cliente.class);
        //Realiza uma operação entre a data de nascimento e a data de agora, o resultado é quantidade de anos do cliente
        cliente.setIdade(Math.toIntExact(ChronoUnit.YEARS.between(novoClienteDto.getDataNascimento(), LocalDate.now())));
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorNome(String nomeCompleto){
        Optional<Cliente> clienteOptional = clienteRepository.findByNomeCompleto(nomeCompleto);
        if (clienteOptional.isPresent()){
            return clienteOptional.get();
        } else {
            throw new ResourceNotFoundException(buscarMensagemDeValidacao("cliente.nome.nao.encontrado", nomeCompleto));
        }
    }

    @Override
    public Cliente buscarPorId(Long idCliente){
        return buscarClientePorId(idCliente);
    }

    @Override
    public Cliente deletarCliente(Long idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        clienteRepository.delete(cliente);
        return cliente;
    }

    @Override
    public Cliente alterarNomeCliente(Long idCliente,@Valid ClienteAlterarNomeDto clienteParaAlterar) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (!clienteParaAlterar.getNomeCompleto().equals(cliente.getNomeCompleto())) {
            cliente.setNomeCompleto(clienteParaAlterar.getNomeCompleto());
            return clienteRepository.save(cliente);
        } else {
            throw new BusinessException(buscarMensagemDeValidacao("cliente.nome.repetido"));
        }
    }
}