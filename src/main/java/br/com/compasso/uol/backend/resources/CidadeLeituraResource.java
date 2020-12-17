package br.com.compasso.uol.backend.resources;

import br.com.compasso.uol.backend.dtos.CidadeRetornadaDto;
import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.services.CidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/compasso-uol/cidade")
public class CidadeLeituraResource {

    private final CidadeService cidadeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CidadeLeituraResource(CidadeService cidadeService, ModelMapper modelMapper) {
        this.cidadeService = cidadeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/procurar-nome")
    public ResponseEntity<CidadeRetornadaDto> buscarCidadePorNome(@RequestParam String nome){
        Cidade cidade = cidadeService.buscarCidadePeloNome(nome);
        return ResponseEntity.ok().body(modelMapper.map(cidade, CidadeRetornadaDto.class));
    }

    @GetMapping(value = "/procurar-estado/{estado}")
    public ResponseEntity<CidadeRetornadaDto[]> buscarCidadesPorEstado(@PathVariable(value = "estado") EstadoEnum estadoEnum){
        List<Cidade> cidades = cidadeService.buscarCidadesPorEstado(estadoEnum);
        return ResponseEntity.ok().body(modelMapper.map(cidades, CidadeRetornadaDto[].class));
    }
}
