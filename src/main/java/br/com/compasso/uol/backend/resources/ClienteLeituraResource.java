package br.com.compasso.uol.backend.resources;

import br.com.compasso.uol.backend.dtos.ClienteRetornadoDto;
import br.com.compasso.uol.backend.models.Cliente;
import br.com.compasso.uol.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/compasso-uol/cliente")
public class ClienteLeituraResource {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteLeituraResource(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/procurado")
    public ResponseEntity<ClienteRetornadoDto> buscarClientePeloNome(@RequestParam String nomeCompleto){
        Cliente cliente = clienteService.buscarClientePorNome(nomeCompleto);
        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteRetornadoDto.class));
    }

    @GetMapping("/{id}/procurado")
    public ResponseEntity<ClienteRetornadoDto> buscarClientePeloId(@PathVariable(value = "id") Long idCliente){
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteRetornadoDto.class));
    }
}
