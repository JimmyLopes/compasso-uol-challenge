package br.com.compasso.uol.backend.dtos;

import br.com.compasso.uol.backend.enums.EstadoEnum;

/**
 * Dto retornando as informação de exibição da cidade
 */
public class CidadeRetornadaDto {

    private String nome;
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
