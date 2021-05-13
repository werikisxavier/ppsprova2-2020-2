package com.ppsdisplayprova2.model.proxy;

import com.ppsdisplayprova2.enums.OrigemEnum;
import com.ppsdisplayprova2.model.Imagem;
import com.ppsdisplayprova2.presenter.VisualizadorPresenter;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class RealImagem implements Imagem {

    private final String nomeArquivo;
    private ImageIcon imagem;

    public RealImagem(String nomeArquivo, OrigemEnum origemImagem) throws Exception {
        this.nomeArquivo = nomeArquivo;
        verificarOrigem(origemImagem);
    }

    @Override
    public void exibir() {
        VisualizadorPresenter.getInstance().exibirTelaPrincipal(imagem);
    }

    private void carregarDoDisco(String nomeArquivo) {
        this.imagem = new ImageIcon(nomeArquivo);
        int width = VisualizadorPresenter.getInstance().getView().getLabelImagem().getWidth();
        int height = VisualizadorPresenter.getInstance().getView().getLabelImagem().getHeight();
        this.imagem.setImage(this.imagem.getImage().getScaledInstance(width, height, 1));
    }

    private void carregarDaInternet(String linkImagem) throws Exception {     
        URL url = new URL(linkImagem);
        this.imagem = new ImageIcon(ImageIO.read(url));
        int width = VisualizadorPresenter.getInstance().getView().getLabelImagem().getWidth();
        int height = VisualizadorPresenter.getInstance().getView().getLabelImagem().getHeight();
        this.imagem.setImage(this.imagem.getImage().getScaledInstance(width, height, 1));
    }

    private void verificarOrigem(OrigemEnum origemImagem) throws Exception {
        if (origemImagem == OrigemEnum.LOCAL) {
            carregarDoDisco(nomeArquivo);
        } else {
            carregarDaInternet(nomeArquivo);
        }
    }

}
