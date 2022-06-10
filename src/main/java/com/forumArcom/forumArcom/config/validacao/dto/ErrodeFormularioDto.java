package com.forumArcom.forumArcom.config.validacao.dto;

public class ErrodeFormularioDto {
    private final String campo;
    private final String erro;

    public ErrodeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
