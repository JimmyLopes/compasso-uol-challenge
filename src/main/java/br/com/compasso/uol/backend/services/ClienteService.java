package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.dtos.ClienteAlterarNomeDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.models.Cliente;

import javax.validation.Valid;

public interface ClienteService {

    /**
     * Método responsavél por salvar um novo Cliente
     *
     * @param novoClienteDto o objeto contendo as informações do novo Cliente
     * @return o novo cliente salvo
     */
    Cliente salvarCliente(@Valid NovoClienteDto novoClienteDto);

    /**
     * Método responsavél por buscar um cliente pelo nome
     *
     * @param nomeCompleto o nome do cliente procurado
     * @return o cliente procurado
     */
    Cliente buscarClientePorNome(String nomeCompleto);

    /**
     * Método responsável por buscar um cliente pelo id
     *
     * @param idCliente o id do cliente procurado
     * @return o cliente procurado
     */
    Cliente buscarPorId(Long idCliente);

    /**
     * Método responsável por deletar um cliente, caso ache ele
     *
     * @param idCliente o id do cliente a ser deletado
     */
    void deletarCliente(Long idCliente);

    /**
     * Método responsável por alterar um nome de um cliente existente
     *
     * @param idCliente o id do cliente
     * @param novoNome o novo nome do cliente
     * @return o cliente com o nome alterado
     */
    Cliente alterarNomeCliente(Long idCliente,@Valid ClienteAlterarNomeDto novoNome);
}
