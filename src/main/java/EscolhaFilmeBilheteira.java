import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class EscolhaFilmeBilheteira {
    private JPanel mainPanel;
    private final AppWindow app;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel bilheteiraLabel = new JLabel("Bilheteira");
    private JLabel nomeFilmeLabel = new JLabel("");
    private JLabel duracaoLabel = new JLabel("");
    private JLabel generosLabel = new JLabel("");
    private JLabel idadeLabel = new JLabel("");
    private JComboBox idiomaComboBox;
    private JComboBox tipoComboBox;
    private JComboBox salaComboBox;


    private BaseDados bd = BaseDados.getInstance();

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public EscolhaFilmeBilheteira(AppWindow app, Filme filme) {
        this.app = app;
        configurarComponentes(filme);
    }

    private void configurarComponentes(Filme filme) {

        List<Filme> filmeSelecionado = new ArrayList<>();  // lista de todos os filmes como mesmo nome de 'filme'

        for(Filme f : bd.getFilmes()) {
            // vai procurar todos os filmes com aquele nome
            if(f.getNome().equals(filme.getNome())) {
                filmeSelecionado.add(f);
            }
        }

        List<Sessao> sessoesDoFilme = new ArrayList<>();

        //todas as sessões existentes
        List<Sessao> listaSessoes = bd.getSessoes();

        // lista de sessoes que queremos mostrar na bilheteira
        List<Sessao> listaSessoesAtivas = new ArrayList<>();

        // Dados dos filmes
        for(Sessao sessao : listaSessoes) {
            if(sessao.getEstado() == Estado.ATIVO)
            {
                listaSessoesAtivas.add(sessao);
            }

        }

        for (Sessao sessao : listaSessoesAtivas)
        {
            for (Filme f: filmeSelecionado)
            {
                if(sessao.getFilme().equals(f))
                {
                    sessoesDoFilme.add(sessao);
                }
            }

        }


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
        nomeFilmeLabel.setFont(new Font("Georgia", Font.PLAIN, 40));
        nomeFilmeLabel.setOpaque(true);
        //--------------------- NOME FILME -------------------------


        //--------------------- DURACAO -------------------------
        duracaoLabel.setText("Duração: " + filme.getDuracao() + " min");
        duracaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        duracaoLabel.setForeground(corFontePreto);
        duracaoLabel.setBackground(corFundo);
        duracaoLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        duracaoLabel.setOpaque(true);
        //--------------------- DURACAO -------------------------


        //--------------------- GENERO -------------------------
        String generos = NormalizarGenero.formatarGeneros(filme.getGeneros());
        generosLabel.setText("Género(s): " + generos );
        generosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        generosLabel.setForeground(corFontePreto);
        generosLabel.setBackground(corFundo);
        generosLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        generosLabel.setOpaque(true);
        //--------------------- GENERO -------------------------

        //--------------------- IDADE -------------------------
        idadeLabel.setText("Idade recomendada: " + filme.getIdade() );
        idadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idadeLabel.setForeground(corFontePreto);
        idadeLabel.setBackground(corFundo);
        idadeLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        idadeLabel.setOpaque(true);
        //--------------------- IDADE -------------------------


        // -------------------- ComboBox idioma --------------------------
        List<Idioma> opcoesIdioma = new ArrayList<>();
        for (Filme f : filmeSelecionado) {
            for (Sessao s: sessoesDoFilme) {
                if (f.getIdiomas().equals(s.getFilme().getIdiomas()) && !opcoesIdioma.contains(f.getIdiomas())) {
                    opcoesIdioma.add(f.getIdiomas());
                }
            }
        }

        idiomaComboBox = new RoundedComboBox<>(opcoesIdioma.toArray(new Idioma[0]), 20);
        idiomaComboBox.setSelectedItem(null); // Placeholder

        idiomaComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(idiomaComboBox) {
                    @Override
                    public void show() {
                        setBorder(BorderFactory.createEmptyBorder());
                        setOpaque(false);
                        super.show();
                    }

                    @Override
                    public void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(corFundoSubMenu);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        g2.dispose();
                    }
                };
                popup.setBorder(BorderFactory.createEmptyBorder());
                popup.setOpaque(false);
                return popup;
            }

            @Override
            protected JButton createArrowButton() {
                return new JButton(new ArrowIcon(idiomaComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        idiomaComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    label.setText("Idioma");
                    label.setForeground(corFontePreto);
                    idiomaComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
                }else if (value instanceof Idioma idioma) {
                    label.setText(idioma == Idioma.VP ? "Português (VP)" : "Inglês (VO)");
                    label.setForeground(corFontePreto);
                    idiomaComboBox.setFont(new Font("Georgia", Font.PLAIN, 20));
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        idiomaComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        idiomaComboBox.setPreferredSize(new Dimension(200, 40));
        idiomaComboBox.setBackground(corFundoComponentes);
        idiomaComboBox.setForeground(corFontePreto);
        idiomaComboBox.setEditable(false);
        idiomaComboBox.setFocusable(false);

        idiomaComboBox.addActionListener(e -> {
            atualizarSalaComboBox(filmeSelecionado);
            if (!salaComboBox.isVisible()) {
                verificarPreenchimentoEContinuar(filmeSelecionado);
            }
        });
        // -------------------- ComboBox idioma --------------------------


        // -------------------- ComboBox sala --------------------------
        salaComboBox = new RoundedComboBox<>(new String[0], 20);

        salaComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null || value.toString().isBlank()) {
                    label.setText("Sala");
                    label.setFont(new Font("Georgia", Font.PLAIN, 25));
                } else {
                    label.setText(value.toString()); // "Normal", "VIP", etc.
                    label.setFont(new Font("Georgia", Font.PLAIN, 25));
                }

                label.setForeground(corFontePreto);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                return label;
            }
        });

        salaComboBox.setVisible(false);
        salaComboBox.setPreferredSize(new Dimension(175, 40));
        salaComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        salaComboBox.setBackground(corFundoComponentes);
        salaComboBox.setForeground(corFontePreto);
        salaComboBox.setFocusable(false);


        // -------------------- ComboBox sala --------------------------


        // -------------------- ComboBox tipo --------------------------
        List<String> opcoesTipo = new ArrayList<>();
        for (Filme f : filmeSelecionado) {
            for(Sessao s: sessoesDoFilme)
            {
                if (f.getTipos().equalsIgnoreCase(s.getFilme().getTipos()) && !opcoesTipo.contains(f.getTipos())) {
                    opcoesTipo.add(f.getTipos());
                }
            }
        }

        tipoComboBox = new RoundedComboBox<>(opcoesTipo.toArray(new String[0]), 20);
        tipoComboBox.setSelectedItem(null); // Placeholder

        tipoComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(tipoComboBox) {
                    @Override
                    public void show() {
                        setBorder(BorderFactory.createEmptyBorder());
                        setOpaque(false);
                        super.show();
                    }

                    @Override
                    public void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(corFundoSubMenu);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        g2.dispose();
                    }
                };
                popup.setBorder(BorderFactory.createEmptyBorder());
                popup.setOpaque(false);
                return popup;
            }

            @Override
            protected JButton createArrowButton() {
                return new JButton(new ArrowIcon(tipoComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        tipoComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null || value.toString().isBlank()) {
                    label.setText("Tipo");
                    label.setFont(new Font("Georgia", Font.PLAIN, 25));
                } else {
                    label.setText(value.toString()); // '2D', '3D', '5D'
                    label.setFont(new Font("Georgia", Font.PLAIN, 25));
                }

                label.setForeground(corFontePreto);

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        tipoComboBox.setFont(new Font("Georgia", Font.PLAIN, 25));
        tipoComboBox.setPreferredSize(new Dimension(150, 40));
        tipoComboBox.setBackground(corFundoComponentes);
        tipoComboBox.setForeground(corFontePreto);
        tipoComboBox.setEditable(false);
        tipoComboBox.setFocusable(false);

        tipoComboBox.addActionListener(e -> {
            atualizarSalaComboBox(filmeSelecionado);
            if (!salaComboBox.isVisible()) {
                verificarPreenchimentoEContinuar(filmeSelecionado);
            }
        });
        // -------------------- ComboBox tipo --------------------------




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(bilheteiraLabel, "x 510, y 30");
        mainPanel.add(cartaz, "x 120, y 300");
        mainPanel.add(nomeFilmeLabel, "x 450, y 290");
        mainPanel.add(duracaoLabel, "x 450, y 405");
        mainPanel.add(generosLabel, "x 450, y 450");
        mainPanel.add(idadeLabel, "x 450, y 495");
        mainPanel.add(idiomaComboBox, "x 450, y 600");
        mainPanel.add(tipoComboBox, "x 750, y 600");
        mainPanel.add(salaComboBox, "x 1000, y 600");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarBilheteira();
            }
        });
    }


    // FUNCAO QUE CRIA E MANIPULA DAS SETAS DOS JComboBoxes
    static class ArrowIcon implements Icon {
        private final int size = 10;
        private final JComboBox<?> comboBox;

        public ArrowIcon(JComboBox<?> comboBox) {
            this.comboBox = comboBox;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            boolean isPopupVisible = comboBox.isPopupVisible();
            g2.translate(x + size / 2, y + size / 2);
            if (isPopupVisible) g2.rotate(Math.PI);
            g2.translate(-(x + size / 2), -(y + size / 2));
            int[] xPoints = {x, x + size, x + size / 2};
            int[] yPoints = {y, y, y + size};
            g2.fillPolygon(xPoints, yPoints, 3);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }

    private void atualizarSalaComboBox(List<Filme> filmeSelecionado) {
        String tipoSelecionado = (String) tipoComboBox.getSelectedItem();
        Idioma idiomaSelecionado = (Idioma) idiomaComboBox.getSelectedItem();

        // Só continua se os dois estiverem preenchidos
        if (tipoSelecionado == null || idiomaSelecionado == null) {
            salaComboBox.setVisible(false);
            return;
        }

        // Só valida para o tipo "2D"
        if (!tipoSelecionado.equals("2D")) {
            salaComboBox.setVisible(false);
            return;
        }

        // Limpa resultados anteriores
        salaComboBox.removeAllItems();

        Set<String> tiposSalaFinal = new HashSet<>();

        List<Sessao> sessoesDoFilme = new ArrayList<>();

        for (Sessao sessao : bd.getSessoes())
        {
            for (Filme filme: filmeSelecionado)
            {
                if(sessao.getFilme().equals(filme))
                {
                    sessoesDoFilme.add(sessao);
                }
            }

        }

        for(Sessao sessao : sessoesDoFilme)
        {
            Filme filme = sessao.getFilme();
            Sala sala = sessao.getSala();

            // Só considera os filmes com o idioma e tipo selecionado
            if (filme.getIdiomas().equals(idiomaSelecionado) && filme.getTipos().equalsIgnoreCase(tipoSelecionado) && sala.getTipo().equalsIgnoreCase("VIP")) {
                Set<String> tiposSala = bd.getTiposSalaDisponiveisParaFilme2D(filme);
                tiposSalaFinal.addAll(tiposSala); // Junta todos os tipos de sala
            }

        }


        if (!tiposSalaFinal.isEmpty()) {
            for (String tipoSala : tiposSalaFinal) {
                salaComboBox.addItem(tipoSala);
            }

            salaComboBox.setSelectedItem(null); // Placeholder
            salaComboBox.setVisible(true);
            salaComboBox.setUI(new BasicComboBoxUI() {
                @Override
                protected ComboPopup createPopup() {
                    BasicComboPopup popup = new BasicComboPopup(salaComboBox) {
                        @Override
                        public void show() {
                            setBorder(BorderFactory.createEmptyBorder());
                            setOpaque(false);
                            super.show();
                        }

                        @Override
                        public void paintComponent(Graphics g) {
                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2.setColor(corFundoSubMenu);
                            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                            g2.dispose();
                        }
                    };
                    popup.setBorder(BorderFactory.createEmptyBorder());
                    popup.setOpaque(false);
                    return popup;
                }

                @Override
                protected JButton createArrowButton() {
                    return new JButton(new ArrowIcon(salaComboBox)) {{
                        setBackground(corBotaoSetaComboBox);
                        setBorder(BorderFactory.createEmptyBorder());
                    }};
                }
            });

            salaComboBox.addActionListener(e -> verificarPreenchimentoEContinuar(filmeSelecionado));
        } else {
            salaComboBox.setVisible(false);
        }
    }



    private void verificarPreenchimentoEContinuar(List<Filme> filmeSelecionado) {
        Idioma idiomaSelecionado = (Idioma) idiomaComboBox.getSelectedItem();
        String tipoSelecionado = (String) tipoComboBox.getSelectedItem();
        String salaSelecionada = salaComboBox.isVisible() ? (String) salaComboBox.getSelectedItem() : null;

        boolean idiomaValido = idiomaSelecionado != null && !idiomaSelecionado.equals("Idioma");
        boolean tipoValido = tipoSelecionado != null && !tipoSelecionado.equals("Tipo");

        if (salaComboBox.isVisible()) {
            boolean salaValida = salaSelecionada != null && !salaSelecionada.equals("Sala");
            if (idiomaValido && tipoValido && salaValida) {
                // Filtrar o filme correto em filmeSelecionado
                Filme filmeFiltrado = null;
                for (Filme f : filmeSelecionado) {
                    boolean idiomaOk = f.getIdiomas().equals(idiomaSelecionado);
                    boolean tipoOk = f.getTipos().equalsIgnoreCase(tipoSelecionado);
                    if (idiomaOk && tipoOk) {
                        filmeFiltrado = f;
                        break;
                    }
                }

                if (filmeFiltrado != null) {
                    // Filtrar salas do tipo selecionado
                    List<Sala> salasFiltradas = new ArrayList<>();
                    for (Sessao sessao : bd.getSessoes()) {
                        if (sessao.getFilme().equals(filmeFiltrado) &&
                                sessao.getSala().getTipo().equalsIgnoreCase(tipoSelecionado)) {
                            salasFiltradas.add(sessao.getSala());
                        }
                    }

                    Filme finalFilme = filmeFiltrado;
                    List<Sala> finalSalas = salasFiltradas;
                    SwingUtilities.invokeLater(() -> app.mostrarPaginaSessoesSalaEscolhida(finalFilme, salaSelecionada));
                }
            }
        } else {
            if (idiomaValido && tipoValido) {
                Filme filmeFiltrado = null;
                for (Filme f : filmeSelecionado) {
                    boolean idiomaOk = f.getIdiomas().equals(idiomaSelecionado);
                    boolean tipoOk = f.getTipos().equalsIgnoreCase(tipoSelecionado);
                    if (idiomaOk && tipoOk) {
                        filmeFiltrado = f;
                        break;
                    }
                }

                if (filmeFiltrado != null) {
                    Filme finalFilme = filmeFiltrado;
                    SwingUtilities.invokeLater(() -> app.mostrarPaginaSessoes(finalFilme));
                }
            }
        }
    }




    public JPanel getMainPanel() {
        return mainPanel;
    }
}
