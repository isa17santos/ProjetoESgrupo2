import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class AdicionarFilme {
    private JPanel mainPanel;
    private JLabel logoLabel;
    private JLabel voltaLabel;
    private JLabel adminLabel;
    private JLabel filmesLabel;
    private JButton adicionarButton;
    private JButton uploadButton;
    private JTextField nomeFilme;


    private final AppWindow app;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundoComponentes = Color.decode("#FFC133");
    private final Color corFundoLabel = Color.decode("#FBA720");
    private final Color corFundo = Color.decode("#F9E6BB");
    private final Color corFonte = Color.decode("#6B3838");
    private final Color corFontePreto = Color.decode("#000000");
    private final Color corFundoUploadButton = Color.decode("#E3E3E3");
    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------



    //construtor
    public AdicionarFilme(AppWindow app) {
        this.app = app;
        configurarComponentes();
    }

    private void configurarComponentes() {

        // pagina principal
        mainPanel.setLayout(new net.miginfocom.swing.MigLayout("nogrid, insets 0"));
        mainPanel.setBackground(corFundo);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagens/cinemagic_logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));


        // seta andar para atras
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/imagens/setaAndarParaAtras.png"));
        Image cartImg = cartIcon.getImage().getScaledInstance(60, 65, Image.SCALE_SMOOTH);
        voltaLabel.setIcon(new ImageIcon(cartImg));

        // --------------------- ADMIN LABEL -----------------------
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adminLabel.setForeground(corFundoLabel);
        adminLabel.setBackground(corFundo);
        adminLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        adminLabel.setOpaque(true);
        // --------------------- ADMIN LABEL -----------------------

        // --------------------- FILMES LABEL -----------------------
        filmesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filmesLabel.setForeground(corFundoLabel);
        filmesLabel.setBackground(corFundo);
        filmesLabel.setFont(new Font("Georgia", Font.PLAIN, 80));
        filmesLabel.setOpaque(true);
        // --------------------- FILMES LABEL -----------------------


        //----------------- BOTAO ADICIONAR -------------
        adicionarButton = new RoundedButton("Adicionar", 20);
        adicionarButton.setFont(new Font("Georgia", Font.PLAIN, 25));
        adicionarButton.setBackground(corFundoLabel);
        adicionarButton.setForeground(corFontePreto); // texto
        //----------------- BOTAO ADICIONAR -------------


        //---------------------------- BOTAO UPLOAD ------------------------------
        uploadButton = new JButton("Carregar Cartaz");
        uploadButton.setFont(new Font("Georgia", Font.PLAIN, 35));
        uploadButton.setBackground(corFundoUploadButton);
        uploadButton.setForeground(corFontePreto);
        uploadButton.setFocusPainted(false);
        uploadButton.setContentAreaFilled(true);
        uploadButton.setBorderPainted(false);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecione uma imagem");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
                fileChooser.addChoosableFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(selectedFile);
                        if (img != null) {
                            uploadButton.setOpaque(false);
                            Image scaledImg = img.getScaledInstance(290, 410, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImg);
                            uploadButton.setIcon(icon);
                            uploadButton.setText(""); // remove texto
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao carregar imagem: " + ex.getMessage());
                    }
                }
            }
        });
        //---------------------------- BOTAO UPLOAD ------------------------------

        // --------------- CAIXA DE TEXTO NOME FILME --------------
        nomeFilme = new RoundedTextField(1,20);
        nomeFilme.setHorizontalAlignment(SwingConstants.CENTER);
        nomeFilme.setBackground(corFundoComponentes);
        nomeFilme.setFont(new Font("Georgia", Font.PLAIN, 35));
        nomeFilme.setText("Nome filme");
        nomeFilme.setForeground(corFonte); // texto
        nomeFilme.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nomeFilme.getText().equals("Nome filme")) {
                    nomeFilme.setText("");
                    nomeFilme.setForeground(corFontePreto); // Cor real do texto do utilizador
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nomeFilme.getText().isEmpty()) {
                    nomeFilme.setText("Nome filme");
                    nomeFilme.setForeground(corFonte); // Placeholder de novo
                }
            }
        });

        // --------------- CAIXA DE TEXTO NOME FILME --------------


        // Adiciona componentes com posicionamento personalizado
        mainPanel.add(logoLabel, "x 20, y 10");
        mainPanel.add(voltaLabel, "x 30, y 200");
        mainPanel.add(adminLabel, "x 550, y 20");
        mainPanel.add(filmesLabel, "x 450, y 110");
        mainPanel.add(adicionarButton, "x 1100, y 210, w 70, h 30");
        mainPanel.add(uploadButton, "x 100, y 290, w 110, h 400");
        mainPanel.add(nomeFilme, "x 450, y 290, w 700, h 50");



        // ------------------- REDIRECIONAMENTOS -------------------
        // Redirecionar para Pagina Principal Admin
        //voltaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //voltaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            //@Override
            //public void mouseClicked(java.awt.event.MouseEvent e) {
                //app.mostrarAdmin();
            //}
        //});

        // Redirecionar para AdicionarFilme
        //adicionarButton.addActionListener(e -> app.mostrarAdicionarFilmes());

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

