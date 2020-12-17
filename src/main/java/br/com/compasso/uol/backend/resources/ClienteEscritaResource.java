package br.com.compasso.uol.backend.resources;

import br.com.compasso.uol.backend.dtos.ClienteAlterarNomeDto;
import br.com.compasso.uol.backend.dtos.ClienteRetornadoDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.models.Cliente;
import br.com.compasso.uol.backend.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/compasso-uol/cliente")
public class ClienteEscritaResource {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteEscritaResource(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteRetornadoDto> salvarCliente(@RequestBody NovoClienteDto novoClienteDto){
        Cliente cliente = clienteService.salvarCliente(novoClienteDto);
        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteRetornadoDto.class));
    }

    @PutMapping("/{cliente}")
    public ResponseEntity<ClienteRetornadoDto> alterarNomeCliente(@PathVariable(value = "cliente") Long idCliente,@RequestBody ClienteAlterarNomeDto clienteAlterarNomeDto){
        Cliente cliente = clienteService.alterarNomeCliente(idCliente, clienteAlterarNomeDto);
        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteRetornadoDto.class));
    }

    @DeleteMapping("/{cliente}")
    public ResponseEntity<Void> excluirCliente(@PathVariable(value = "cliente") Long idCliente) {
        clienteService.deletarCliente(idCliente);
        return ResponseEntity.ok().build();
    }
}
