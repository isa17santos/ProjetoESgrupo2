import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EscolherLugar {
    private JPanel mainPanel;
    private final AppWindow app;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel bilheteiraLabel = new JLabel("Bilheteira");
    private JLabel salaLabel = new JLabel("");
    private JLabel nomeFilmeLabel = new JLabel("");
    private JLabel ecraIconLabel = new JLabel();
    private JLabel ecraLabel = new JLabel("");


    Set<JButton> lugaresSelecionados = new HashSet<>();


    private List<JButton> botoesLugares = new ArrayList<>();
    private JButton lugarSelecionado = null;

    private JButton confirmarButton;


    private boolean comSala;


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

    private final Color corLugarLivre = Color.decode("#FAFAFA");
    private final Color corLugarBloqueado = Color.decode("#BEB7B7");
    private final Color corLugarOcupado = Color.decode("#FF5050");
    private final Color corLugarAcessibilidade = Color.decode("#66BDFF");
    private final Color corLugarSelecionado = Color.decode("#FFC133");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------

    //construtor
    public EscolherLugar(AppWindow app, Sessao sessao, boolean comSala) {
        this.app = app;
        this.comSala = comSala;
        configurarComponentes(sessao);
    }

    private void configurarComponentes(Sessao sessao) {


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


        // --------------------- SALA LABEL -----------------------
        salaLabel.setText(sessao.getSala().getDesignacao());
        salaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salaLabel.setForeground(corFundoLabel);
        salaLabel.setBackground(corFundo);
        salaLabel.setFont(new Font("Georgia", Font.PLAIN, 100));
        salaLabel.setOpaque(true);
        // --------------------- SALA LABEL -----------------------


        // --------------------- NOME FILME LABEL -----------------------
        nomeFilmeLabel.setText(sessao.getFilme().getNome());
        nomeFilmeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nomeFilmeLabel.setForeground(corFontePreto);
        nomeFilmeLabel.setBackground(corFundo);
        nomeFilmeLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        nomeFilmeLabel.setOpaque(true);
        // --------------------- NOME FILME LABEL -----------------------


        // seta andar para atras
        ImageIcon ecraIcon = new ImageIcon(getClass().getResource("/imagens/imagemEcra.png"));
        Image ecraImg = ecraIcon.getImage().getScaledInstance(1100, 45, Image.SCALE_SMOOTH);
        ecraIconLabel.setIcon(new ImageIcon(ecraImg));


        // --------------------- ECRA LABEL -----------------------
        ecraLabel.setText("Ecrã");
        ecraLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ecraLabel.setForeground(corFontePreto);
        ecraLabel.setBackground(corFundo);
        ecraLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        ecraLabel.setOpaque(true);
        // --------------------- ECRA LABEL -----------------------


        // ---------------------- BOTAO CONFIRMAR ------------------
        confirmarButton = new RoundedButton("Confirmar", 20);
        confirmarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        confirmarButton.setBackground(corFundoComponentes);
        confirmarButton.setForeground(corFontePreto);
        confirmarButton.setFocusPainted(false);
        confirmarButton.setVisible(false);

        confirmarButton.addActionListener(e -> {
            if (!lugaresSelecionados.isEmpty()) {
                for (JButton btn : lugaresSelecionados) {
                    btn.setBackground(corLugarOcupado);
                    btn.setEnabled(false);

                    int index = botoesLugares.indexOf(btn);
                    if (index != -1) {
                        sessao.ocuparLugar(index); // atualiza lugares ocupados
                    }
                }

                lugaresSelecionados.clear(); // limpa seleção
                confirmarButton.setVisible(false);

                bd.gravarDados(); // guarda dados atualizados
                app.mostrarCarrinho(); // redireciona para o carrinho
            }
        });
// ---------------------- BOTAO CONFIRMAR ------------------



//---------------- GRID LUGARES ----------------------
        Set<Point> lugaresAcessiveis = new HashSet<>();

        JPanel painelLugares = new JPanel();
        painelLugares.setLayout(new GridLayout(sessao.getSala().getNumFilas(), sessao.getSala().getNumLugaresFila() + 1, 25, 25));
        painelLugares.setOpaque(false);
        painelLugares.setBackground(corFundo);

        int numFilas = sessao.getSala().getNumFilas();
        int lugaresPorFila = sessao.getSala().getNumLugaresFila();

        List<Integer> lugaresOcupados = sessao.getLugaresOcupados();

        int filaCentralStart = numFilas / 2 - 1;
        int[] filasAcessiveis = {filaCentralStart, filaCentralStart + 1};

        ImageIcon iconAcessivel = new ImageIcon(getClass().getResource("/imagens/cadeira-de-rodas.png"));
        Image imgEscalada = iconAcessivel.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        iconAcessivel = new ImageIcon(imgEscalada);

        botoesLugares.clear();

        int indiceLugarGlobal = 0;
        boolean salaTemAcessibilidade = sessao.getSala().temAcessibilidade();

        for (int fila = 0; fila < numFilas; fila++) {
            int colunaReal = 0;

            for (int lugar = 0; lugar < lugaresPorFila + 1; lugar++) {
                if (lugar == lugaresPorFila / 2) {
                    painelLugares.add(Box.createHorizontalStrut(30)); // corredor central
                    continue;
                }

                boolean tempAcessivel = false;
                if (salaTemAcessibilidade) {
                    tempAcessivel =
                            (fila == filasAcessiveis[0] && (colunaReal == 0 || colunaReal == lugaresPorFila - 1)) ||
                                    (fila == filasAcessiveis[1] && (colunaReal == 0 || colunaReal == lugaresPorFila - 1));

                    if (tempAcessivel) {
                        lugaresAcessiveis.add(new Point(fila, colunaReal));
                    }
                }

                final boolean isAcessivel = tempAcessivel;

                JButton lugarBtn = new RoundedButton("", 35);
                lugarBtn.setPreferredSize(new Dimension(20, 40));
                lugarBtn.setOpaque(false);
                lugarBtn.setContentAreaFilled(false);
                lugarBtn.setFocusPainted(false);
                lugarBtn.setBorderPainted(false);

                boolean isOcupado = lugaresOcupados.contains(indiceLugarGlobal);

                if (isOcupado) {
                    lugarBtn.setBackground(corLugarOcupado);
                    lugarBtn.setEnabled(false);
                } else {
                    Color corOriginal = isAcessivel ? corLugarAcessibilidade : corLugarLivre;
                    lugarBtn.setBackground(corOriginal);

                    int finalIndiceLugar = indiceLugarGlobal;
                    lugarBtn.addActionListener(e -> {
                        if (lugaresSelecionados.contains(lugarBtn)) {
                            // Deselecionar
                            lugarBtn.setBackground(isAcessivel ? corLugarAcessibilidade : corLugarLivre);
                            lugaresSelecionados.remove(lugarBtn);
                        } else {
                            // Selecionar
                            lugarBtn.setBackground(corLugarSelecionado);
                            lugaresSelecionados.add(lugarBtn);
                        }

                        confirmarButton.setVisible(!lugaresSelecionados.isEmpty());
                    });
                }

                if (isAcessivel) {
                    lugarBtn.setIcon(iconAcessivel);
                }

                botoesLugares.add(lugarBtn);
                painelLugares.add(lugarBtn);

                colunaReal++;
                indiceLugarGlobal++;
            }
        }
//---------------- GRID LUGARES ----------------------




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(bilheteiraLabel, "x 510, y 30");
        mainPanel.add(salaLabel, "x 585, y 140");
        mainPanel.add(nomeFilmeLabel, "pos 0.5al 260");
        mainPanel.add(ecraIconLabel, "x 140, y 305");
        mainPanel.add(ecraLabel, "x 90, y 310");
        mainPanel.add(confirmarButton, "x 1050, y 185, w 100, h 40");
        mainPanel.add(painelLugares, "x 150, y 390, w 1050, h 300");



        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(comSala)
                {
                    app.mostrarPaginaSessoesSalaEscolhida(sessao.getFilme(), sessao.getSala().getTipo());
                }
                else{
                    app.mostrarPaginaSessoes(sessao.getFilme());
                }

            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
