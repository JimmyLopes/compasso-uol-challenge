package br.com.compasso.uol.backend.enums;

public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    INDEFINIDO("Indefinido");

    private final String sexo;

    SexoEnum(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
