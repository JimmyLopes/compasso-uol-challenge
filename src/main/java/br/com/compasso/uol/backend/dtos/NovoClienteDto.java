package br.com.compasso.uol.backend.dtos;

import br.com.compasso.uol.backend.enums.SexoEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Dto com as informações necessárias para cadastrar novo cliente
 */
public class NovoClienteDto {

    @Size(min = 3, max = 100, message = "{cliente.nome.completo.tamanho.invalido}")
    @NotBlank(message = "{cliente.nome.completo.vazio}")
    private String nomeCompleto;

    @NotNull(message = "{cliente.sexo.nulo}")
    private SexoEnum sexoEnum;

    @NotNull(message = "{cliente.data.nascimento.nulo}")
    @DateTimeFormat(pattern = "dd.MM.yy")
    @Temporal(value= TemporalType.TIMESTAMP)
    private LocalDate dataNascimento;

    @NotNull(message = "{cliente.cidade.nulo}")
    private Long idCidade;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public SexoEnum getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(SexoEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }
}
