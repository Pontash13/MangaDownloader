package org.example.View;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class App
{
    //Criando compontes simples para a tela
    private JFrame miJFrame = new JFrame("Manga Downloader");
    private JPanel miJPanel = new JPanel();
    private JLabel miJLabel = new JLabel();
    private JTextArea miJTextArea = new JTextArea(5,20);


    //Iniciar o
    public void Init_view()
    {
        miJFrame.setSize(500,300);
        miJPanel.setSize(300, 300);
        miJPanel.setLayout(new GridBagLayout());
        miJLabel.setText("Estou fazendo testes");
        miJPanel.add(miJLabel);
        miJPanel.add(miJTextArea);
        miJFrame.add(miJPanel);
        miJFrame.setVisible(true);
    }
}
