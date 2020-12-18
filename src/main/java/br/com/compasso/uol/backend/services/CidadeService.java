package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.models.Cidade;

import javax.validation.Valid;
import java.util.List;

public interface CidadeService {

    /**
     * Método responsavél por salvar uma nova Cidade
     *
     * @param novaCidadeDto a classe contendo as informações da nova Cidade
     * @return a nova Cidade salva
     */
    Cidade salvarCidade(@Valid NovaCidadeDto novaCidadeDto);

    /**
     * Método responsavél por buscar uma cidade por nome
     *
     * @param nomeCidade o nome da cidade procurada
     * @return a cidade procurada
     */
    Cidade buscarCidadePeloNome(String nomeCidade);

    /**
     * Método responsavél por buscar uma lista de cidade de acordo com o seu estado
     *
     * @param estado o estado
     * @return a lista de cidades daquele estado
     */
    List<Cidade> buscarCidadesPorEstado(EstadoEnum estado);
}
