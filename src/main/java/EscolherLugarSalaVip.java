import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EscolherLugarSalaVip {
    private JPanel mainPanel;
    private final AppWindow app;
    private JLabel logoLabel = new JLabel();
    private JLabel voltaLabel = new JLabel();
    private JLabel bilheteiraLabel = new JLabel("Bilheteira");
    private JLabel salaLabel = new JLabel("");
    private JLabel nomeFilmeLabel = new JLabel("");
    private JLabel zonaVipIconLabel = new JLabel();
    private JLabel zonaVipLabel = new JLabel("");
    private JLabel ecraIconLabel = new JLabel();
    private JLabel ecraLabel = new JLabel("");


    Set<JButton> lugaresSelecionados = new HashSet<>();


    private List<JButton> botoesLugares = new ArrayList<>();
    private JButton lugarSelecionado = null;

    private JButton confirmarButton;

    private boolean filtragem;

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
    public EscolherLugarSalaVip(AppWindow app, Sessao sessao, boolean filtragem) {
        this.app = app;
        this.filtragem = filtragem;
        configurarComponentes(sessao);
    }

    private void configurarComponentes(Sessao sessao) {

        System.out.println(sessao.getSala().getDesignacao());

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


        // icon ecra
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
                        int fila = index / sessao.getSala().getNumLugaresFila();
                        int lugar = index % sessao.getSala().getNumLugaresFila();
                        String rotuloLugar = "F" + (fila + 1) + "-L" + (lugar + 1); // atenção: +1 porque o utilizador vê a partir do 1

                        Bilhete bilhete = new Bilhete(sessao, rotuloLugar);

                        ObjetoCarrinho objeto = new ObjetoCarrinho(bilhete, 1);
                        bd.adicionarAoCarrinho(objeto);
                    }
                }

                lugaresSelecionados.clear(); // limpa seleção
                confirmarButton.setVisible(false);

                app.mostrarCarrinho(); // redireciona para o carrinho
            }
        });
// ---------------------- BOTAO CONFIRMAR ------------------




        //---------------- GRID LUGARES ----------------------
        Set<Point> lugaresAcessiveis = new HashSet<>();

        JPanel painelLugares = new JPanel();
        painelLugares.setLayout(new BoxLayout(painelLugares, BoxLayout.Y_AXIS));
        painelLugares.setOpaque(true);
        painelLugares.setBackground(corFundo);

        int larguraBotao = 60;
        int alturaBotao = 60;
        int espacamentoHorizontal = 25;
        int espacamentoVertical = 10;
        int larguraCorredor = 80;

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

        int primeiraVip = (int) (numFilas * 0.75);
        boolean zonaVipAdicionada = false;

        for (int fila = 0; fila < numFilas; fila++) {
            if (fila == primeiraVip && !zonaVipAdicionada) {
                JPanel zonaVipPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
                zonaVipPanel.setOpaque(false);

                JLabel zonaVipLabel = new JLabel("Zona Vip");
                zonaVipLabel.setForeground(corFontePreto);
                zonaVipLabel.setBackground(corFundo);
                zonaVipLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
                zonaVipLabel.setOpaque(true);

                JLabel zonaVipIconLabel = new JLabel();
                ImageIcon zonaVipIcon = new ImageIcon(getClass().getResource("/imagens/imagemEcra.png"));
                Image zonaVipImg = zonaVipIcon.getImage().getScaledInstance(1170, 45, Image.SCALE_SMOOTH);
                zonaVipIconLabel.setIcon(new ImageIcon(zonaVipImg));

                zonaVipPanel.add(zonaVipLabel);
                zonaVipPanel.add(Box.createHorizontalStrut(10));
                zonaVipPanel.add(zonaVipIconLabel);

                painelLugares.add(zonaVipPanel);
                zonaVipAdicionada = true;
            }

            JPanel filaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, espacamentoHorizontal, espacamentoVertical));
            filaPanel.setOpaque(false);

            int colunaReal = 0;
            for (int lugar = 0; lugar < lugaresPorFila + 1; lugar++) {
                if (lugar == lugaresPorFila / 2) {
                    filaPanel.add(Box.createRigidArea(new Dimension(larguraCorredor, alturaBotao)));
                    continue;
                }

                final boolean isVip = (fila >= primeiraVip);

                boolean tempAcessivel = false;
                if (salaTemAcessibilidade) {
                    tempAcessivel =
                            (fila == filasAcessiveis[0] && (colunaReal == 0 || colunaReal == lugaresPorFila)) ||
                                    (fila == filasAcessiveis[1] && (colunaReal == 0 || colunaReal == lugaresPorFila)) ||
                                    (isVip && (lugar == 0 || lugar == lugaresPorFila));
                }

                final boolean isAcessivel = tempAcessivel;

                JButton lugarBtn = new RoundedButton("", 35);
                lugarBtn.setPreferredSize(new Dimension(larguraBotao, alturaBotao));
                lugarBtn.setMinimumSize(new Dimension(larguraBotao, alturaBotao));
                lugarBtn.setMaximumSize(new Dimension(larguraBotao, alturaBotao));
                lugarBtn.setOpaque(false);
                lugarBtn.setContentAreaFilled(false);
                lugarBtn.setFocusPainted(false);
                lugarBtn.setBorderPainted(false);

                boolean isOcupado = lugaresOcupados.contains(indiceLugarGlobal);

                if (!isVip) {
                    lugarBtn.setBackground(corLugarBloqueado);
                    lugarBtn.setEnabled(false);
                } else {
                    if (isOcupado) {
                        lugarBtn.setBackground(corLugarOcupado);
                        lugarBtn.setEnabled(false);
                    } else {
                        Color corOriginal = isAcessivel ? corLugarAcessibilidade : corLugarLivre;
                        lugarBtn.setBackground(corOriginal);

                        lugarBtn.addActionListener(e -> {
                            if (lugaresSelecionados.contains(lugarBtn)) {
                                lugarBtn.setBackground(isAcessivel ? corLugarAcessibilidade : corLugarLivre);
                                lugaresSelecionados.remove(lugarBtn);
                            } else {
                                lugarBtn.setBackground(corLugarSelecionado);
                                lugaresSelecionados.add(lugarBtn);
                            }
                            confirmarButton.setVisible(!lugaresSelecionados.isEmpty());
                        });
                    }
                }

                if (isAcessivel) {
                    lugarBtn.setIcon(iconAcessivel);
                }

                botoesLugares.add(lugarBtn);
                filaPanel.add(lugarBtn);
                colunaReal++;
                indiceLugarGlobal++;
            }

            painelLugares.add(filaPanel);
        }

        JScrollPane scrollPaneLugares = new JScrollPane(painelLugares);
        scrollPaneLugares.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneLugares.setOpaque(false);
        scrollPaneLugares.getViewport().setOpaque(false);
        scrollPaneLugares.setVerticalScrollBarPolicy(numFilas > 4 ? JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED : JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneLugares.setHorizontalScrollBarPolicy(lugaresPorFila > 10 ? JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED : JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneLugares.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneLugares.getHorizontalScrollBar().setUnitIncrement(16);
//---------------- GRID LUGARES ----------------------




        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(bilheteiraLabel, "x 510, y 30");
        mainPanel.add(salaLabel, "x 510, y 140");
        mainPanel.add(nomeFilmeLabel, "pos 0.5al 260");
        mainPanel.add(ecraIconLabel, "x 140, y 305");
        mainPanel.add(ecraLabel, "x 90, y 310");
        mainPanel.add(confirmarButton, "x 1050, y 185, w 100, h 40");
        mainPanel.add(scrollPaneLugares, "x 35, y 360, w 1170, h 365");




        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voltaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(filtragem)
                {
                    app.mostrarBilheteira();
                }
                else {
                    app.mostrarPaginaSessoesSalaEscolhida(sessao.getFilme(), "VIP");
                }
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
