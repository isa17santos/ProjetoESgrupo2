import javax.swing.*;
import java.awt.*;
import java.util.Map;

class BarChartPanel extends JPanel {
    private Map<String, Integer> data;

    //---------------------------- DEFINIÇÃO DE CORES ---------------------------------------------
    private final Color corFundo = Color.decode("#F9E6BB");

    public BarChartPanel() {
        setBackground(corFundo); // corFundo
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.isEmpty()) return;

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / data.size();
        int max = data.values().stream().max(Integer::compareTo).orElse(1);

        int i = 0;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int barHeight = (int) ((double) entry.getValue() / max * (height - 60));
            g.setColor(new Color(0xFFC133));
            g.fillRect(i * barWidth + 20, height - barHeight - 40, barWidth - 40, barHeight);
            g.setColor(Color.BLACK);
            g.drawString(entry.getKey(), i * barWidth + 20, height - 20);
            g.drawString(String.valueOf(entry.getValue()), i * barWidth + 20, height - barHeight - 45);
            i++;
        }
    }
}