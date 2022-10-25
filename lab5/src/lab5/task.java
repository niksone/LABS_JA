package lab5;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task extends JFrame {
    static int frameWidth = 1200;
    static int frameHeight = 600;

    public static void main(String[] args) {
        new task("JAVA LAB", frameWidth, frameHeight);
    }

    task(String s, int w, int h) {
        super(s);
        setSize(w, h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void paint(Graphics g) {
        super.paint(g);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBackground(new Color(185,205,229));
        Font textFont = new Font("Serif", Font.PLAIN, 30);
        Font titleFont = new Font("Serif", Font.BOLD, 42);
        Color textColor = Color.BLACK;
        int xOffset = 30;
        int yOffset = 20;
        String code = "1986";
        JLabel title = new JLabel("Авторизація входу");
        title.setFont(titleFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, yOffset, frameWidth, 50);
        title.setForeground(textColor);
        jpanel.add(title);

//      Phone Input
        JLabel phoneLabel = new JLabel("Телефон");
        phoneLabel.setFont(textFont);
        phoneLabel.setBounds(xOffset, yOffset * 2 + 50, 200, 50);
        phoneLabel.setForeground(textColor);
        jpanel.add(phoneLabel);

        JTextField phoneInput = new JTextField();
        phoneInput.setBorder(new LineBorder(Color.BLUE,1));
        phoneInput.setFont(textFont);
        phoneInput.setBounds(xOffset + 200, yOffset * 2 + 50, 400, 50);
        jpanel.add(phoneInput);


//        Code Input
        try {
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(
                            Objects.requireNonNull(getClass().getResource("img.png"))
                    ).getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT)
            );

            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(xOffset + 75, yOffset * 3 + 100, 150, 200);
            jpanel.add(imgLabel);
        } catch (Exception e){
            System.out.println("Image not found");
        }


        JLabel codeLabel = new JLabel("<HTML> Введіть чотири останні цифри номеру, <br> з якого Вам телефонують:</HTML>");
        codeLabel.setFont(textFont);
        codeLabel.setBounds(xOffset + 300, yOffset * 3 + 100, 700, 100);
        codeLabel.setForeground(textColor);
        jpanel.add(codeLabel);

        JTextField codeInput = new JTextField();
        codeInput.setBorder(new LineBorder(Color.BLUE,1));
        codeInput.setFont(textFont);
        codeInput.setBounds(xOffset + 500, yOffset * 3 + 200, 150, 50);
        jpanel.add(codeInput);



//        Footer
        int letterWidth = 24;
        String recallBtnText = "Повторний дзвінок";
        int recallBtnWidth = letterWidth  * recallBtnText.length();
        JButton recallBtn = new JButton(recallBtnText);
        System.out.println(recallBtnText.length());
        System.out.println(recallBtnText.length() * letterWidth);

        recallBtn.addActionListener(actionEvent -> {
            codeInput.setText("");
            recallBtn.setEnabled(false);
            recallBtn.setBackground(Color.GRAY);
        });

        recallBtn.setFont(textFont);
        recallBtn.setBounds(xOffset, yOffset * 4 + 300, recallBtnWidth, 70);
        recallBtn.setBackground(Color.YELLOW);
        jpanel.add(recallBtn);

        String sendBtnText = "Надіслати";
        JButton sendBtn = new JButton(sendBtnText);
        sendBtn.setEnabled(false);
        int sendBtnWidth = letterWidth  * sendBtnText.length();

        sendBtn.setFont(textFont);
        sendBtn.setBounds(xOffset * 2 + recallBtnWidth, yOffset * 4 + 300, sendBtnWidth, 70);
        sendBtn.setBackground(Color.GRAY);
        jpanel.add(sendBtn);

        sendBtn.addActionListener(actionEvent -> {
            Pattern phonePattern = Pattern.compile("\\d{10}");
            Matcher phoneMatcher = phonePattern.matcher(phoneInput.getText());

            Pattern codePattern = Pattern.compile("\\d{4}");
            Matcher codeMatcher = codePattern.matcher(codeInput.getText());
            if(!phoneMatcher.matches()){
                showDialog("Wrong phone format", false);
                return;
            }
            if(!codeMatcher.matches()){
                showDialog("Wrong code format", false);
                return;
            }
            if(Objects.equals(codeInput.getText(), code)){
                showDialog("Ви увійшли", true);
            } else {
                recallBtn.setEnabled(true);
                recallBtn.setBackground(Color.YELLOW);
            }
            System.out.println("send");
        });

        String cancelBtnText = "Відмінити";
        JButton cancelBtn = new JButton(cancelBtnText);
        int cancelBtnWidth = letterWidth  * cancelBtnText.length();

        cancelBtn.setFont(textFont);
        cancelBtn.setBounds(xOffset * 3 + recallBtnWidth + sendBtnWidth, yOffset * 4 + 300, cancelBtnWidth, 70);
        cancelBtn.setBackground(Color.RED);
        jpanel.add(cancelBtn);
        add(jpanel);

        cancelBtn.addActionListener(actionEvent -> {
            System.out.println("cancel");
            System.exit(0);
        });

        phoneInput.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(Objects.equals(codeInput.getText(), "") || Objects.equals(phoneInput.getText(), "")){
                    sendBtn.setEnabled(false);
                    sendBtn.setBackground(Color.GRAY);
                } else {
                    sendBtn.setEnabled(true);
                    sendBtn.setBackground(Color.GREEN);
                }
            }
        });


        codeInput.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(Objects.equals(codeInput.getText(), "") || Objects.equals(phoneInput.getText(), "")){
                    sendBtn.setEnabled(false);
                    sendBtn.setBackground(Color.GRAY);
                } else {
                    sendBtn.setEnabled(true);
                    sendBtn.setBackground(Color.GREEN);
                }
                recallBtn.setEnabled(false);
                recallBtn.setBackground(Color.GRAY);
            }

        });

//        showDialog("TEST");
    }
}