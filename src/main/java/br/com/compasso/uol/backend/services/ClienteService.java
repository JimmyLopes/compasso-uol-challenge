package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.dtos.ClienteAlterarNomeDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.models.Cliente;

import javax.validation.Valid;

public interface ClienteService {
    Cliente salvarCliente(@Valid NovoClienteDto novoClienteDto);

    Cliente buscarClientePorNome(String nomeCompleto);

    Cliente buscarPorId(Long idCliente);

    Cliente deletarCliente(Long idCliente);

    Cliente alterarNomeCliente(Long idCliente, ClienteAlterarNomeDto novoNome);
}
