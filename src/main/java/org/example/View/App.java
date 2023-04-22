package org.example.View;

import org.example.Config.MethodsProt;
import org.example.Controller.MangaController;
import org.example.Model.Chapter;
import org.example.Model.ChapterModels.Release;
import org.example.Model.ListManga;
import org.example.Model.Manga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class App extends JFrame {
    private JTextField fieldSearch;
    private JButton buttonSearch;
    private JButton buttonDownload;
    private JTable tableResults;
    private JEditorPane imagePane;


    private MangaController mangaController = new MangaController();


    // Cria a janela
    public App()
    {
        setTitle("Manga Downloader :v");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use um FlowLayout para organizar os componentes
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        fieldSearch = new JTextField(50);
        panel.add(fieldSearch);

        buttonSearch = new JButton("Pesquisar");
        panel.add(buttonSearch);

        buttonDownload = new JButton("Download");
        panel.add(buttonDownload);

        tableResults = new JTable();
        tableResults.setDefaultEditor(Object.class, null);
        panel.add(new JScrollPane(tableResults));

        imagePane = new JEditorPane();
        imagePane.setContentType("text/html");
        imagePane.setPreferredSize(new Dimension(285, 400));
        panel.add(new JScrollPane(imagePane));


        add(panel);
        setVisible(true);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initSearchManga();
            }
        });

        buttonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initDownload();
            }
        });


        tableResults.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
                    Integer row = tableResults.getSelectedRow();
                    Integer idSerie = (Integer) model.getValueAt(row, 0);
                    initSearchCaps(idSerie);
                }
            }
            public void mousePressed(MouseEvent event) {
                Point p = event.getPoint();
                Integer row = tableResults.rowAtPoint(p);
                Integer col = tableResults.columnAtPoint(p);

                if (row >= 0 && col >= 0)
                {
                    DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
                    String link = (String) model.getValueAt(row, 5);
                    String description = (String) model.getValueAt(row, 6);

                    imagePane.setText("<html><center><img src='" + link + "' width='230' height='270'><br> " +
                            "</center>" +
                            "<p>" + description + "</p>" +
                            "</html>");
                }
            }
        });

        setVisible(true);
    }

    private void initSearchManga()
    {
        String[] columns = {"ID Série", "Título", "Autor", "Nota", "Status", "link", "descricao"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        String term = fieldSearch.getText();

        initLoading();
        ListManga listManga = mangaController.serchManga(term);

        if(listManga == null)
        {
            JOptionPane.showMessageDialog(null,
                    "Manga não encontrado\nTente outro título", "Erro!", JOptionPane.WARNING_MESSAGE);
        }
        else
        {

            tableModel.setRowCount(0);
            // Adiciona as linhas da lista de mangás à tabela
            for (Manga manga : listManga.getListManga()) {
                tableModel.addRow(new Object[] {manga.getIdSerie(), manga.getName(),
                        manga.getAuthor(), manga.getScore(),
                        manga.isComplete() ? "Completo" : "Em lançamento", manga.getCover(),
                        manga.getDescription()});
            }
            tableResults.setModel(tableModel);

            TableColumnModel columnModel = tableResults.getColumnModel();
            TableColumn lastColumn1 = columnModel.getColumn(columnModel.getColumnCount() - 1);
            lastColumn1.setWidth(0);
            lastColumn1.setMinWidth(0);
            lastColumn1.setMaxWidth(0);

            TableColumn lastColumn2 = columnModel.getColumn(columnModel.getColumnCount() - 2);
            lastColumn2.setWidth(0);
            lastColumn2.setMinWidth(0);
            lastColumn2.setMaxWidth(0);
        }

    }


    private void initSearchCaps(Integer idSerie) {
        String[] columns = {"Número Capítulo", "Nome", "Data", "idRelease", "nameManga"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        initLoading();
        List<Chapter> listChapter = mangaController.searchChapters(idSerie);

        if (listChapter == null)
        {
            JOptionPane.showMessageDialog(null,
                    "O Manga não tem capítulos\nTente outro título", "Erro!", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            tableModel.setRowCount(0);
            for (Chapter cap : listChapter)
            {
                Release release = (Release) MethodsProt.getItemByIndexMap(cap.getReleases(), 0);
                tableModel.addRow(new Object[] {
                        cap.getNumber(), cap.getChapter_name(),
                        cap.getDate(),  release.getId_release(), cap.getName()
                });
            }
            tableResults.setModel(tableModel);

            TableColumnModel columnModel = tableResults.getColumnModel();
            TableColumn lastColumn1 = columnModel.getColumn(columnModel.getColumnCount() - 1);
            lastColumn1.setWidth(0);
            lastColumn1.setMinWidth(0);
            lastColumn1.setMaxWidth(0);

            TableColumn lastColumn2 = columnModel.getColumn(columnModel.getColumnCount() - 2);
            lastColumn2.setWidth(0);
            lastColumn2.setMinWidth(0);
            lastColumn2.setMaxWidth(0);
        }
    }


    private void initLoading()
    {
        String[] columns = {"-"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[] {"CARREGANDO...."});
        tableResults.setModel(tableModel);
    }


    private void initDownload()
    {
        for(int selectedRows : tableResults.getSelectedRows())
        {
            DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
            String cap = (String) model.getValueAt(selectedRows, 0);
            Integer link = (Integer) model.getValueAt(selectedRows, 3);
            String name  = (String) model.getValueAt(selectedRows, 4);
            mangaController.downloadChapter(cap ,link, name);
        }
    }
}
