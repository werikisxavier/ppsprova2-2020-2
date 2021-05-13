package com.ppsdisplayprova2.model.proxy;

import com.ppsdisplayprova2.enums.OrigemEnum;
import com.ppsdisplayprova2.model.Imagem;

public class ProxyImagem implements Imagem {

    private RealImagem imagemReal;
    private final String nomeArquivo;
    private OrigemEnum origemImagem;


    public ProxyImagem(String nomeArquivo, OrigemEnum origemImagem) {
        this.nomeArquivo = nomeArquivo;
        this.origemImagem = origemImagem;
    }

    @Override
    public void exibir() throws Exception {
        if (imagemReal == null) {
            imagemReal = new RealImagem(nomeArquivo,origemImagem);
        }
        imagemReal.exibir();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

}
