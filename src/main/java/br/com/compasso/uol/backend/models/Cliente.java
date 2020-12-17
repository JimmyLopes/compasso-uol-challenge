package br.com.compasso.uol.backend.models;

import br.com.compasso.uol.backend.enums.SexoEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cliente_tb")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nomeCompleto;

    @Column(name = "sexo", nullable = false)
    private SexoEnum sexoEnum;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 3)
    private Integer idade;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public SexoEnum geteSexo() {
        return sexoEnum;
    }

    public void seteSexo(SexoEnum sexoEnum) {
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
