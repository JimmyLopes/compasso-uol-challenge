package br.com.compasso.uol.backend.models;

import br.com.compasso.uol.backend.enums.EstadoEnum;

import javax.persistence.*;

@Entity
@Table(name = "cidade_tb")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cidade_id", nullable = false)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEnum estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoEnum geteEstado() {
        return estado;
    }

    public void seteEstado(EstadoEnum estado) {
        this.estado = estado;
    }
}
