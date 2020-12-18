package br.com.compasso.uol.backend.configuration;

import br.com.compasso.uol.backend.dtos.NovaCidadeDto;
import br.com.compasso.uol.backend.dtos.NovoClienteDto;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.models.Cliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Ajusta o mapeamento das entidades
     *
     * @return o modelMapper com o mapeamento das entidades
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<NovaCidadeDto, Cidade> novaCidadeDtoToCidadePropertyMap = new PropertyMap<NovaCidadeDto, Cidade>() {
            @Override
            protected void configure() {
                map().seteEstado(source.getEstado());
            }
        };
        modelMapper.addMappings(novaCidadeDtoToCidadePropertyMap);

        PropertyMap<NovoClienteDto, Cliente> novoClienteDtoToClientePropertyMap = new PropertyMap<NovoClienteDto, Cliente>() {
            @Override
            protected void configure() {
                map().seteSexo(source.getSexoEnum());
            }
        };
        modelMapper.addMappings(novoClienteDtoToClientePropertyMap);

        return modelMapper;
    }
}
