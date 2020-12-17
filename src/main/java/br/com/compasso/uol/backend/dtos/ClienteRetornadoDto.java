package br.com.compasso.uol.backend.dtos;

import java.time.LocalDate;

public class ClienteRetornadoDto {

    private String nomeCompleto;
    private String sexoEnum;
    private LocalDate dataNascimento;
    private Integer idade;
    private CidadeRetornadaDto cidade;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(String sexoEnum) {
        this.sexoEnum = sexoEnum;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public CidadeRetornadaDto getCidade() {
        return cidade;
    }

    public void setCidade(CidadeRetornadaDto cidade) {
        this.cidade = cidade;
    }
}
