package br.com.compasso.uol.backend.services;

import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.models.Cidade;

import javax.validation.Valid;
import java.util.List;

public interface CidadeService {
    Cidade salvarCidade(@Valid NovaCidadeDto novaCidadeDto);
    Cidade buscarCidadePeloNome(String nomeCidade);
    List<Cidade> buscarCidadesPorEstado(EstadoEnum estado);
}
