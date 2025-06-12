import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class PaginaEscolhaSessao {
    private JPanel mainPanel;
    private final AppWindow app;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel bilheteiraLabel = new JLabel("Bilheteira");
    private JLabel sessaoLabel = new JLabel("Sessões");
    private JLabel nomeFilmeLabel = new JLabel("");
    private JLabel duracaoLabel = new JLabel("");
    private JLabel tipoFilmeLabel = new JLabel("");

    private Filme filme;


    private BaseDados bd = BaseDados.getInstance();

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoUploadButton = Color.decode("#E3E3E3");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public PaginaEscolhaSessao(AppWindow app, Filme filme) {
        this.app = app;
        this.filme = filme;
        configurarComponentes(filme);
    }

    private void configurarComponentes(Filme filme) {

        List<Sessao> sessoes = new ArrayList<>();

        for(Sessao s : bd.getSessoes()) {
            // vai procurar todos os filmes com aquele nome
            if(filme.getNome().equals(s.getFilme().getNome()) && filme.getIdiomas().equals(s.getFilme().getIdiomas()) && filme.getTipos().equals(s.getFilme().getTipos())) {
                sessoes.add(s);
            }
        }

        //-----------debug-----------
        System.out.println(filme.getNome());
        System.out.println(filme.getIdiomas());
        System.out.println(filme.getTipos());
        //-----------debug-----------


        // pagina principal
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- BILHETEIRA LABEL -----------------------
        bilheteiraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bilheteiraLabel.setForeground(corFundoLabel);
        bilheteiraLabel.setBackground(corFundo);
        bilheteiraLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        bilheteiraLabel.setOpaque(true);
        // --------------------- BILHETEIRA LABEL -----------------------


        // --------------------- SESSOES LABEL -----------------------
        sessaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sessaoLabel.setForeground(corFundoLabel);
        sessaoLabel.setBackground(corFundo);
        sessaoLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        sessaoLabel.setOpaque(true);
        // --------------------- SESSOES LABEL -----------------------


        //--------------------- CARTAZ -------------------------
        JLabel cartaz = new JLabel();
        ImageIcon cartazIcon = new ImageIcon(getClass().getResource("/imagens/cartazes/" + filme.getFoto()));
        Image cartazImg = cartazIcon.getImage().getScaledInstance(265, 400, Image.SCALE_SMOOTH);
        cartaz.setIcon(new ImageIcon(cartazImg));
        //--------------------- CARTAZ -------------------------

        //--------------------- NOME FILME -------------------------
        nomeFilmeLabel.setText(filme.getNome());
        nomeFilmeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nomeFilmeLabel.setForeground(corFontePreto);
        nomeFilmeLabel.setBackground(corFundo);
        nomeFilmeLabel.setFont(new Font("Georgia", Font.PLAIN, 55));
        nomeFilmeLabel.setOpaque(true);
        //--------------------- NOME FILME -------------------------


        //--------------------- DURACAO -------------------------
        duracaoLabel.setText("Duração: " + filme.getDuracao() + " min");
        duracaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        duracaoLabel.setForeground(corFontePreto);
        duracaoLabel.setBackground(corFundo);
        duracaoLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        duracaoLabel.setOpaque(true);
        //--------------------- DURACAO -------------------------


        //--------------------- TIPO -------------------------
        tipoFilmeLabel.setText("Filme " + filme.getTipos());
        tipoFilmeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tipoFilmeLabel.setForeground(corFontePreto);
        tipoFilmeLabel.setBackground(corFundo);
        tipoFilmeLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        tipoFilmeLabel.setOpaque(true);
        //--------------------- TIPO -------------------------


        //--------------------- TABELA SESSOES -------------------------

        Map<LocalDate, List<Sessao>> sessoesPorData = new TreeMap<>();

        for (Sessao s : sessoes) {
            try {
                LocalDate data = LocalDate.of(s.getAno(), s.getMes(), s.getDia());
                sessoesPorData.putIfAbsent(data, new ArrayList<>());
                sessoesPorData.get(data).add(s);
            } catch (DateTimeException e) {
                System.err.println("Erro ao converter data da sessão: " + e.getMessage());
            }
        }

        JScrollPane tabela = criarTabelaSessoesPorDia(sessoesPorData);

        //--------------------- TABELA SESSOES -------------------------



        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(bilheteiraLabel, "x 510, y 30");
        mainPanel.add(sessaoLabel, "x 565, y 140");
        mainPanel.add(cartaz, "x 120, y 300");
        mainPanel.add(nomeFilmeLabel, "x 450, y 290");
        mainPanel.add(duracaoLabel, "x 455, y 364");
        mainPanel.add(tipoFilmeLabel, "x 695, y 364");
        mainPanel.add(tabela, "x 455, y 405, w 755, h 290");


        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarEscolhaFilmeBilhteira(filme);
            }
        });
    }

    private JScrollPane criarTabelaSessoesPorDia(Map<LocalDate, List<Sessao>> sessoesAgrupadasPorDia) {
        JPanel tabelaPanel = new JPanel();
        tabelaPanel.setLayout(new BoxLayout(tabelaPanel, BoxLayout.Y_AXIS));
        tabelaPanel.setBackground(corFundoSubMenu);
        tabelaPanel.setBorder(BorderFactory.createLineBorder(corFundoComponentes, 2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM", new Locale("pt", "PT"));

        for (Map.Entry<LocalDate, List<Sessao>> entry : sessoesAgrupadasPorDia.entrySet()) {
            LocalDate data = entry.getKey();
            List<Sessao> sessoes = entry.getValue();

            // Linha = 1 data + 1 grupo de horários
            JPanel linhaPanel = new JPanel(new BorderLayout());
            linhaPanel.setBackground(corFundoSubMenu);
            linhaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, corFundoComponentes)); // linha inferior

            // Coluna da data
            JLabel dataLabel = new JLabel(formatter.format(data).toLowerCase());
            dataLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            dataLabel.setForeground(corFontePreto);
            dataLabel.setOpaque(true);
            dataLabel.setBackground(corFundoSubMenu);
            dataLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, corFundoComponentes)); // borda direita (separa colunas)
            dataLabel.setPreferredSize(new Dimension(200, 30));
            dataLabel.setHorizontalAlignment(SwingConstants.CENTER);

            linhaPanel.add(dataLabel, BorderLayout.WEST);

            // Coluna dos horários
            JPanel horariosPanel = new JPanel(new GridBagLayout());
            horariosPanel.setBackground(corFundoSubMenu);

// constraints para centralizar os botões com espaçamento horizontal
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 12, 0, 12); // espaçamento entre botões

            int col = 0;
            for (Sessao sessao : sessoes) {
                LocalTime horaSessao = LocalTime.of(sessao.getHora(), sessao.getMinuto());
                String horario = horaSessao.format(DateTimeFormatter.ofPattern("HH:mm"));
                JButton botao = new RoundedButton(horario, 20);
                botao.setFont(new Font("Georgia", Font.PLAIN, 20));
                botao.setPreferredSize(new Dimension(100, 45));
                botao.setBackground(corHoverComboBox);
                botao.setForeground(corFontePreto);
                botao.setFocusPainted(false);
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botao.setHorizontalAlignment(SwingConstants.CENTER);
                botao.setVerticalAlignment(SwingConstants.CENTER);

                botao.addActionListener(e -> app.mostrarEscolherLugar(sessao));

                gbc.gridx = col++;
                horariosPanel.add(botao, gbc);
            }


            // Scroll horizontal se necessário
            JScrollPane scrollHorarios = new JScrollPane(horariosPanel,
                    JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollHorarios.setBorder(null);
            scrollHorarios.getHorizontalScrollBar().setUnitIncrement(16);
            scrollHorarios.setBackground(corFundoSubMenu);
            scrollHorarios.getViewport().setBackground(corFundoSubMenu);

            linhaPanel.add(scrollHorarios, BorderLayout.CENTER);

            tabelaPanel.add(linhaPanel);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaPanel);
        scrollPane.setPreferredSize(new Dimension(750, 350));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createLineBorder(corFundoComponentes, 1));
        scrollPane.getViewport().setBackground(corFundoSubMenu);

        return scrollPane;
    }





    public JPanel getMainPanel() {
        return mainPanel;
    }
}
