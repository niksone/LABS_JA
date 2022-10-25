package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class task extends JFrame {
    static int frameWidth = 1920;
    static int frameHeight = 1080;
    private JPopupMenu popup;
    private JLabel[] productImgs;
    private String currentImgName;
    public static void main(String[] args) {
        new task("JAVA LAB", frameWidth, frameHeight);
    }

    task(String s, int w, int h) {
        super(s);
        setSize(w, h);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void drawProductInput(JPanel jpanel, JLabel label, JTextField name, JTextField price, int offsetY, int currentIndex) {
        Font textFont = new Font("Serif", Font.PLAIN, 30);
        Color textColor = Color.BLACK;
        label.setFont(textFont);
        label.setBounds(0, offsetY, 150, 50);
        label.setForeground(textColor);
        jpanel.add(label);

        name.setFont(textFont);
        name.setBounds(200, offsetY, 500, 50);
        name.setForeground(textColor);
        jpanel.add(name);

        price.setFont(textFont);
        price.setBounds(750, offsetY, 200, 50);
        price.setForeground(textColor);
        try {
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(
                            Objects.requireNonNull(getClass().getResource("sneaker.jpg"))
                    ).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)
            );

            JLabel imgLabel = new JLabel(icon);
            imgLabel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    showPopup(me, currentIndex);
                }
            }) ;
            imgLabel.setBounds(1000, offsetY, 100, 100);
            productImgs[currentIndex] = imgLabel;
            jpanel.add(imgLabel);
        } catch (Exception e){
            System.out.println("Image not found");
        }

        jpanel.add(price);
    }

    public static void drawProductLine(JPanel jpanel, JLabel name, int offsetY) {
        int letterWidth = 20;
        Font textFont = new Font("Serif", Font.PLAIN, 30);
        Color textColor = Color.BLACK;
        name.setFont(textFont);
        name.setBounds(0, offsetY, frameWidth, 50);
        name.setForeground(textColor);

        jpanel.add(name);
        jpanel.repaint();
    }

    public String validateInput(JTextField[] names, JTextField[] prices){
        for (JTextField name: names) {
            if(Objects.equals(name.getText(), "")){
                return "Form contain empty product name";
            }
        }

        for (JTextField price: prices ) {
            if(Objects.equals(price.getText(), "")){
                return "Form contain empty product price";
            }
            if(Float.parseFloat(price.getText()) < 0){
                return "Form contain negative product price";
            }
        }
        return "";
    }

    private void showDialog(String message, boolean exitOnClose)
    {
        Font textFont = new Font("Serif", Font.PLAIN, 30);

        JDialog errorDialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);
        errorDialog.setLayout(null);

        JLabel text = new JLabel(message);
        text.setFont(textFont);
        text.setBounds(0, 0, 600, 50);
        errorDialog.add(text);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        String recallBtnText = "Продовжити";
        JButton recallBtn = new JButton(recallBtnText);
        recallBtn.addActionListener(actionEvent -> {
            errorDialog.setVisible(false);
            if(exitOnClose) System.exit(0);
        });

        recallBtn.setFont(textFont);
        recallBtn.setBounds(100, 100, 400, 70);
        errorDialog.add(recallBtn);

        errorDialog.setBounds(300, 200, 600, 300);
        errorDialog.setVisible(true);
    }

    public void calculate(JPanel jpanel, int productsCount, JTextField[] priceInput, JTextField[] namesInput, JLabel[] resultsLabels, JButton closeBtn){
        int minPrice = Integer.MAX_VALUE;
        int minPriceIndex = 0;

        for (int i = 0; i < productsCount; i++) {
            if (Objects.equals(priceInput[i].getText(), "")) {
                continue;
            }
            int price = Integer.parseInt(priceInput[i].getText());
            if (price < minPrice) {
                minPrice = price;
                minPriceIndex = i;
            }
        }
        resultsLabels[0].setText("Найдешевший Товар: " + namesInput[minPriceIndex].getText() + "    Ціна: " + priceInput[minPriceIndex].getText());
        int maxPrice = Integer.MIN_VALUE;
        int maxPriceIndex = 0;
        for (int i = 0; i < productsCount; i++) {
            if (Objects.equals(priceInput[i].getText(), "")) {
                continue;
            }
            int price = Integer.parseInt(priceInput[i].getText());
            if (price > maxPrice) {
                maxPrice = price;
                maxPriceIndex = i;
            }
        }
        resultsLabels[1].setText("Найдорожчий Товар: " + namesInput[maxPriceIndex].getText() + "    Ціна: " + priceInput[maxPriceIndex].getText());
        closeBtn.setVisible(true);

        jpanel.repaint();
    }

    void showPopup(MouseEvent me, int currentIndex) {
        if(me.isPopupTrigger()) {
            popup.show(me.getComponent(), me.getX(), me.getY());
            popup.setName(String.valueOf(currentIndex));
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBackground(new Color(185, 205, 229));
        Font textFont = new Font("Serif", Font.PLAIN, 30);

        int productsCount = 5;
        JTextField[] namesInput = new JTextField[productsCount];
        JTextField[] priceInput = new JTextField[productsCount];
        JLabel[] resultsLabels = new JLabel[2];
        productImgs = new JLabel[productsCount];
        for (int i = 0; i < productsCount; i++) {
            namesInput[i] = new JTextField();
            priceInput[i] = new JTextField();

            drawProductInput(
                    jpanel,
                    new JLabel(i + 1 + " Товар"),
                    namesInput[i],
                    priceInput[i],
                    110 * i,
                    i
            );
        }

        JButton submitBtn = new JButton("Надіслати");

        submitBtn.setFont(textFont);
        submitBtn.setBounds(0, 110 * (productsCount + 1), 200, 70);
        submitBtn.setBackground(Color.GREEN);
        jpanel.add(submitBtn);


        JButton closeBtn = new JButton("CLOSE");

        closeBtn.setFont(textFont);
        closeBtn.setBounds(250, 110 * (productsCount + 1), 200, 70);
        closeBtn.setBackground(Color.RED);
        closeBtn.setVisible(false);
        jpanel.add(closeBtn);

        for (int i = 0; i < resultsLabels.length; i++) {
            resultsLabels[i] = new JLabel("");

            drawProductLine(
                    jpanel,
                    resultsLabels[i],
                    productsCount * 110 + 200 + 60 * i
            );
        }
        submitBtn.addActionListener(actionEvent -> {
            String formValidationError = validateInput(namesInput, priceInput);

            if(!Objects.equals(formValidationError, "")) {
                showDialog(formValidationError, false);
                return;
            }
            calculate(jpanel, productsCount, priceInput, namesInput, resultsLabels, closeBtn);
        });


        closeBtn.addActionListener(actionEvent -> {
            System.exit(0);
        });

        JMenuBar jMenuBar = new JMenuBar();
        JMenu program = new JMenu("Програма");
        JMenu edit = new JMenu("Правка");
        jMenuBar.add(program);
        jMenuBar.add(edit);
        setJMenuBar(jMenuBar);

        JMenuItem check = program.add(new JMenuItem("Перевірка"));
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String formValidationError = validateInput(namesInput, priceInput);
                if(Objects.equals(formValidationError, "")) return;
                showDialog(formValidationError, false);
            }
        });
        program.add(new JSeparator());

        JMenuItem calculate = program.add(new JMenuItem("Обчислити"));
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String formValidationError = validateInput(namesInput, priceInput);
                if(!Objects.equals(formValidationError, "")) {
                    showDialog(formValidationError, false);
                    return;
                }

                calculate(jpanel, productsCount, priceInput, namesInput, resultsLabels, closeBtn);
            }
        });
        program.add(new JSeparator());

        JMenuItem quit = program.add(new JMenuItem("Вийти"));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        edit.add(new JMenuItem("Копіювати"));
        edit.add(new JMenuItem("Вставити"));

        popup = new JPopupMenu();
        JMenuItem change = new JMenuItem("Змінити");
        JMenuItem delete = new JMenuItem("Видалити");
        JMenuItem addBorder = new JMenuItem("Додати рамку");


        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (JLabel img: productImgs) {
                    img.setVisible(false);
                }
            }
        });

        addBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (JLabel img: productImgs) {
                    img.setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }
        });

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currentImgName = Objects.equals(currentImgName, "t-shirt.jpg") ? "sneaker.jpg" : "t-shirt.jpg";

                for (JLabel img: productImgs) {
                    try {
                        ImageIcon icon = new ImageIcon(
                                new ImageIcon(
                                        Objects.requireNonNull(getClass().getResource(currentImgName))
                                ).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)
                        );

                        img.setIcon(icon);
                    } catch (Exception e){
                        System.out.println("Image not found");
                    }
                }
            }
        });

        popup.add(change);
        popup.add(addBorder);
        popup.add(delete);

        add(popup);

        add(jpanel);
    }


}