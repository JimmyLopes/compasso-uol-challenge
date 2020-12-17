package br.com.compasso.uol.backend.resources;

import br.com.compasso.uol.backend.dtos.CidadeRetornadaDto;
import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.services.CidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compasso-uol/cidade")
public class CidadeEscritaResource {

    private final CidadeService cidadeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CidadeEscritaResource(CidadeService cidadeService,
                                 ModelMapper modelMapper) {
        this.cidadeService = cidadeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CidadeRetornadaDto> salvarCidade(@RequestBody NovaCidadeDto novaCidadeDto){
        Cidade cidade = cidadeService.salvarCidade(novaCidadeDto);
        return ResponseEntity.ok().body(modelMapper.map(cidade, CidadeRetornadaDto.class));
    }
}
