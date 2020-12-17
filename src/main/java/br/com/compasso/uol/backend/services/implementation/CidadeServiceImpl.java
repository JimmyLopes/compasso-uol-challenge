package br.com.compasso.uol.backend.services.implementation;

import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.exceptions.ResourceNotFoundException;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.repositorys.CidadeRepository;
import br.com.compasso.uol.backend.services.CidadeService;
import br.com.compasso.uol.backend.utils.ListUtils;
import br.com.compasso.uol.backend.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;
    private final ModelMapper modelMapper;

    public CidadeServiceImpl(CidadeRepository cidadeRepository, ModelMapper modelMapper) {
        this.cidadeRepository = cidadeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Cidade salvarCidade(@Valid NovaCidadeDto novaCidadeDto) {
        return cidadeRepository.save(modelMapper.map(novaCidadeDto, Cidade.class));
    }

    @Override
    public Cidade buscarCidadePeloNome(String nomeCidade) {
        Optional<Cidade> cidade = cidadeRepository.findByNome(nomeCidade);
        if (cidade.isPresent())  return cidade.get();
        else throw new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("cidade.nao.encontrada", nomeCidade));
    }

    @Override
    public List<Cidade> buscarCidadesPorEstado(EstadoEnum estadoEnum) {
        List<Cidade> cidades = ListUtils.getListFromIterable(cidadeRepository.findAllByEstado(estadoEnum));
        if (!cidades.isEmpty()){
            return cidades;
        } else {
            throw new ResourceNotFoundException(StringUtils.buscarMensagemDeValidacao("cidade.estado.sem.cidade", estadoEnum.getNome()));
        }
    }
}
