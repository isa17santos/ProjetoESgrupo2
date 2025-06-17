import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class PaginaEscolhaFilmeEditar {

    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel adminLabel = new JLabel("Admin");
    private JLabel filmesLabel = new JLabel("Filme - Editar");
    private JComboBox <Object> filmesComboBox = new JComboBox<>();
    private JButton editarButton = new JButton("Editar");

    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoSubMenu = Color.decode("#FBDC95");
    private final Color corBotaoSetaComboBox = Color.decode("#F2AF14");
    private final Color corHoverComboBox = Color.decode("#FCD373");

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    // Construtor
    public PaginaEscolhaFilmeEditar(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {
        // Dados dos filmes
        List<Sessao> sessoes = BaseDados.getInstance().getSessoes();
        for(Sessao sessao : sessoes) {
            sessao.getFilme().setComSessao(true);
        }

        List<Filme> filmes = BaseDados.getInstance().getFilmes();
        List<Filme> filmesSemSessao = new ArrayList<>();

        //apenas filmes que nao estejam em sessoes

        for (Filme filme : filmes) {
            if(filme.isComSessao()==false){
                filmesSemSessao.add(filme);
            }
        }

        //------ evita que aparecam nomes de filmes repetidos
        List<String> opcoesFilmes = new ArrayList<>();
        for(Filme filme : filmesSemSessao){
            if(!opcoesFilmes.contains(filme.getNome())){
                opcoesFilmes.add(filme.getNome());
            }
        }


        // Página principal
        mainPanel.setLayout(new MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));

        // Seta andar para trás
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // Admin label
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        adminLabel.setOpaque(true);

        // Salas label
        filmesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filmesLabel.setForeground(corFundoLabel);
        filmesLabel.setBackground(corFundo);
        filmesLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        filmesLabel.setOpaque(true);

        // ComboBox de filmes
        filmesComboBox = new RoundedComboBox<>(opcoesFilmes.toArray(new String[0]), 20);
        // Não selecionar nenhum item no início → mostra placeholder
        filmesComboBox.setSelectedItem(null);
        filmesComboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = new BasicComboPopup(filmesComboBox) {
                    @Override
                    public void show() {
                        // Tira a borda preta
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
                return new JButton(new ArrowIcon(filmesComboBox)) {{
                    setBackground(corBotaoSetaComboBox);
                    setBorder(BorderFactory.createEmptyBorder());
                }};
            }
        });

        // Custom renderer com placeholder
        filmesComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Placeholder (quando nada está selecionado)
                if (value == null) {
                    label.setText("Filme");
                    label.setForeground(corFontePreto);
                    filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 40));
                } else {
                    label.setForeground(corFontePreto);
                    filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 30));
                }

                if (index == -1) label.setBackground(corFundoComponentes);
                else if (isSelected) label.setBackground(corHoverComboBox);
                else label.setBackground(corFundoSubMenu);

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                return label;
            }
        });

        filmesComboBox.setFont(new Font("Georgia", Font.PLAIN, 40));
        filmesComboBox.setBackground(corFundoComponentes);
        filmesComboBox.setForeground(corFontePreto);
        filmesComboBox.setEditable(false);
        filmesComboBox.setFocusable(false);


        // Botão Editar
        editarButton = new RoundedButton("Editar", 20);
        editarButton.setFont(new Font("Georgia", Font.PLAIN, 40));
        editarButton.setBackground(corFundoLabel);
        editarButton.setForeground(corFontePreto); // texto

        // Reposicionamento dos componentes
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(filmesLabel, "x 400, y 120");
        mainPanel.add(filmesComboBox, "x 150, y 300, w 600, h 70");
        mainPanel.add(editarButton, "x 800, y 300, w 350, h 70");

        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Página Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarPaginaPrincipalFilmesAdmin();
            }
        });

        // Redirecionar para EditarFilme
        editarButton.addActionListener(e -> {
            Object selectedFilme = filmesComboBox.getSelectedItem();
            System.out.println(selectedFilme);
            Filme filmeSelecionado = null;
            for(Filme filme : filmesSemSessao){
                if(filme.getNome().equals(selectedFilme))
                {
                    filmeSelecionado = filme;
                    break;
                }
            }

            if (selectedFilme != null) {
                app.mostrarEditarFilmes(filmeSelecionado);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um filme para editar.", "Seleção do Filme", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
