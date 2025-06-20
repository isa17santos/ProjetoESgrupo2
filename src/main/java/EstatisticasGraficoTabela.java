import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.table.JTableHeader;

public class EstatisticasGraficoTabela extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private BarChartPanel barChartPanel = new BarChartPanel();
    private JScrollPane tableScrollPane = new JScrollPane();
    private JTable table;

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

    public EstatisticasGraficoTabela() {
        setLayout(cardLayout);
        setBackground(corFundo); // corFundo
        barChartPanel.setBackground(corFundo); // corFundo
        add(barChartPanel, "BAR");
        add(tableScrollPane, "TABLE");
    }

    // Show bar chart with data
    public void showBarChart(Map<String, Integer> data) {
        barChartPanel.setData(data);
        cardLayout.show(this, "BAR");
    }

    // Show table with data
    public void showTable(String[] columns, Object[][] data) {
        table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Georgia", Font.PLAIN, 18));
        table.setBackground(corFundoSubMenu); // Table background

        // Set header color (different yellow)
        JTableHeader header = table.getTableHeader();
        header.setBackground(corBotaoSetaComboBox);
        header.setFont(new Font("Georgia", Font.BOLD, 20));

        // Set scroll pane and viewport background to match table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(corFundoSubMenu);
        scrollPane.setBackground(corFundoSubMenu);

        removeAll();
        add(scrollPane);
        revalidate();
        repaint();
    }


    public void clear() {
        removeAll(); // Remove all components (table or chart)
        add(barChartPanel, "BAR"); // Ensure bar chart panel is present
        barChartPanel.setData(null); // Clear chart data
        table = null; // Remove table reference
        revalidate();
        repaint();
        cardLayout.show(this, "BAR");
    }

}