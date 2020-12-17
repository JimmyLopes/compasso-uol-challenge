package br.com.compasso.uol.backend.dtos;

import br.com.compasso.uol.backend.enums.EstadoEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NovaCidadeDto {
    @Size(min = 3, max = 100, message = "{cidade.nome.tamanho.invalido}")
    @NotBlank(message = "{cidade.nome.vazio}")
    private String nome;

    @NotNull(message = "{cidade.estado.nulo}")
    private EstadoEnum estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }
}
