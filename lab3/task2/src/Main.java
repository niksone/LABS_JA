
import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class Main extends JFrame {
    JPanel jpanel;
    public static void main(String[] args) {
        new Main("JAVA LAB", 1200, 600);
    }

    Main(String s, int w, int h) {
        super(s);

        jpanel = new JPanel();
        jpanel.setLayout(null);
        setSize(w, h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(jpanel);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Font textFont = new Font("Serif",  Font.BOLD | Font.ITALIC, 20);
        Color[] colorPerSeason = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};

        Integer[] temperaturePerMonth = {-3, -12, 5, 12, 20, 25, 30, 20, 10, 5, 2, -2};
        Integer[] temperatureBySeason = {0,0,0,0};

        for (int i = 0; i < 4; i++) {
            int counter = 0;
            for (int j = -1; j < 2; j++) {
                int currentIndex = j + i * 3;
                if(currentIndex < 0) currentIndex = temperaturePerMonth.length + currentIndex;
                counter += temperaturePerMonth[currentIndex];
            }
            temperatureBySeason[i] = counter / 3;
        }
        g.drawLine(0, 400, 1200, 400);

        for (int i = 0; i < temperatureBySeason.length; i++) {
            g.setColor(colorPerSeason[i]);
            int barHeight = Math.abs(temperatureBySeason[i] * 5);
            int posY = temperatureBySeason[i] > 0 ? 400 - temperatureBySeason[i] * 5 : 400 - temperatureBySeason[i] * 5 - barHeight;
            int posYLabel = temperatureBySeason[i] > 0 ? 320 : 320 + barHeight;

            g.fillRect(100 + i * 150, posY, 120, barHeight);
            JLabel polygonLabel = new JLabel(String.valueOf(temperatureBySeason[i]));
            polygonLabel.setFont(textFont);
            polygonLabel.setForeground(colorPerSeason[i]);
            polygonLabel.setBounds(100 + i * 150, posYLabel, 120 ,20);
            jpanel.add(polygonLabel);
        }
    }
}