package com.ppsdisplayprova2.model.proxy;

import com.ppsdisplayprova2.enums.OperacaoEnum;
import com.ppsdisplayprova2.model.Imagem;

public class ProxyImagem implements Imagem {

    private RealImagem imagemReal;
    private final String nomeArquivo;
    private OperacaoEnum operacao;

    public ProxyImagem(String nomeArquivo, OperacaoEnum operacao) {
        this.nomeArquivo = nomeArquivo;
        this.operacao = operacao;
    }

    @Override
    public void exibir() throws Exception {
        if (imagemReal == null) {
            imagemReal = new RealImagem(nomeArquivo, operacao);
        }
        imagemReal.exibir();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

  
}
