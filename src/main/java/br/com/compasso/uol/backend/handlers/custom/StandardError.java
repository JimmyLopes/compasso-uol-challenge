package br.com.compasso.uol.backend.handlers.custom;

/**
 * Classe de erro padrão
 */
public class StandardError {

    private String erro;

    public StandardError() {
    }

    public StandardError(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
