package com.ppsdisplayprova2.presenter;

import com.ppsdisplayprova2.enums.OperacaoEnum;
import com.ppsdisplayprova2.model.proxy.ProxyImagem;
import com.ppsdisplayprova2.view.VisualizadorView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class VisualizadorPresenter {

    private static VisualizadorPresenter instance;

    private VisualizadorView view;
    private ArrayList<ProxyImagem> imagens;

    private VisualizadorPresenter() {
        this.view = new VisualizadorView();
        this.view.setVisible(true);
        imagens = new ArrayList<>();
        this.view.setTitle("Visualizador de imagens");
        carregarImagens();
        iniciarListeners();
    }

    public static synchronized VisualizadorPresenter getInstance() {
        if (instance == null) {
            instance = new VisualizadorPresenter();
        }
        return instance;
    }

    public void exibirMiniImagens(ArrayList<ProxyImagem> imagens) throws Exception {
        int location = 20;
        for (ProxyImagem imagem : imagens) {
            ImageIcon img = new ImageIcon(imagem.getNomeArquivo());
            img.setImage(img.getImage().getScaledInstance(130, 110, 1));
            img.setDescription(imagem.getNomeArquivo());
            JButton button = new JButton();
            button.setSize(130, 110);
            button.setLocation(35, location);
            button.setIcon(img);
            criarListener(button);
            this.view.getJpanelImagens().add(button);
            location += 125;
        }
    }

    private void carregarScrollPane() {
        JScrollPane scrollPane = new JScrollPane(view.getJpanelImagens(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBounds(710, 150, 220, 400);
        view.getJpanelImagens().setBounds(0, 0, 120, 900);
        view.getJpanelImagens().setPreferredSize(new Dimension(183, 900));
        view.getContentPane().add(scrollPane);
    }

    private void getSelecionado(String descricao, ArrayList<ProxyImagem> imagens) {
        for (ProxyImagem imagem : imagens) {
            if (imagem.getNomeArquivo().equals(descricao)) {
                try {
                    imagem.exibir();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                }
            }

        }
    }

    private void criarListener(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSelecionado(button.getIcon().toString(), imagens);
            }
        });
    }

    public void exibirTelaPrincipal(ImageIcon imagem) {
        this.view.getLabelImagem().setIcon(imagem);
    }

    public VisualizadorView getView() {
        return view;
    }

    private void carregarImagens() {
        try {
            imagens.add(new ProxyImagem("Imagem1_10Mb.jpg", OperacaoEnum.LOCAL));
            imagens.add(new ProxyImagem("Imagem2_10Mb.jpg", OperacaoEnum.LOCAL));
            imagens.add(new ProxyImagem("Imagem3_10Mb.jpg", OperacaoEnum.LOCAL));
            imagens.add(new ProxyImagem("Imagem4_10Mb.jpg", OperacaoEnum.LOCAL));
            imagens.add(new ProxyImagem("Imagem5_10Mb.jpg", OperacaoEnum.LOCAL));

            exibirMiniImagens(imagens);
            carregarScrollPane();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error ao carregar as imagens!");
        }
    }

    private void iniciarListeners() {
        this.view.getBtInserirLink().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String link = JOptionPane.showInputDialog(view, "Digite o link da imagem desejada:");

                if (link != null) {
                    if (!link.contains(".png") && !link.contains(".jpg")) {
                        JOptionPane.showMessageDialog(view, "Error: Link inválido, digite um link .png ou .jpg!");
                    } else {
                        try {
                            imagens.add(new ProxyImagem(link, OperacaoEnum.WEB));
                            exibirMiniImagens(imagens);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(view, "Error ao carregar as imagens!");
                        }
                    }
                }

            }
        });
    }

}
