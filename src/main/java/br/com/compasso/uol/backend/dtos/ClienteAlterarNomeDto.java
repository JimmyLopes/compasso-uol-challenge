package br.com.compasso.uol.backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Dto para alterar o nome do cliente
 */
public class ClienteAlterarNomeDto {

    @NotBlank(message = "{cliente.nome.alterado.vazio}")
    @Size(min = 3, message = "{cliente.nome.alterado.tamanho}")
    private String nomeCompleto;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
