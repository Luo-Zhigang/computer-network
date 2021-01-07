package email;


import java.awt.EventQueue;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 472, 543);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        textField = new JTextField();
        textField.setFont(new Font("宋体", Font.BOLD, 15));
        textField.setBounds(92, 10, 301, 32);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u53D1\u9001\u65B9:");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 72, 28);
        contentPane.add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setFont(new Font("宋体", Font.BOLD, 15));
        textField_1.setBounds(92, 52, 301, 32);
        contentPane.add(textField_1);

        JLabel lblNewLabel_2 = new JLabel("\u63A5\u6536\u65B9:");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel_2.setBounds(10, 52, 72, 28);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_1 = new JLabel("\u4E3B\u9898:");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel_1.setBounds(10, 94, 72, 28);
        contentPane.add(lblNewLabel_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setFont(new Font("宋体", Font.BOLD, 15));
        textField_2.setBounds(92, 94, 301, 32);
        contentPane.add(textField_2);

        JLabel lblNewLabel_1_1 = new JLabel("\u5185\u5BB9:");
        lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(10, 139, 72, 28);
        contentPane.add(lblNewLabel_1_1);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("宋体", Font.BOLD, 15));
        textArea.setBounds(10, 177, 438, 212);
        textArea.setLineWrap(true);
        contentPane.add(textArea);

        //发送
        JButton btnNewButton = new JButton("\u53D1\u9001");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获得接收方的输入内容
                String fieldText = textField.getText();
                //获得发送方的输入内容
                String textField_1Text = textField_1.getText();
                //获得主题的输入内容
                String textField_2Text = textField_2.getText();
                //获得邮箱内容
                String textAreaText = textArea.getText();
                Email email = new Email();
                if (fieldText.equals("") || textField_1Text.equals("")) {
                    JOptionPane.showMessageDialog(null, "发送方或接收方为空，请重新输入", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                } else if (textField_2Text.equals("") || textAreaText.equals("")) {
                    JOptionPane.showMessageDialog(null, "主题或内容为空，请重新输入", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        email.setEmail(fieldText, textField_1Text, textField_2Text, textAreaText);
                        textField.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textArea.setText("");
                        JOptionPane.showMessageDialog(null, "发送成功!", "right", JOptionPane.INFORMATION_MESSAGE);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 17));
        btnNewButton.setBounds(10, 418, 72, 32);
        contentPane.add(btnNewButton);

        //清除
        JButton btnNewButton_1 = new JButton("\u6E05\u9664");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textArea.setText("");
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 17));
        btnNewButton_1.setBounds(195, 418, 72, 32);
        contentPane.add(btnNewButton_1);

        //退出
        JButton btnNewButton_2 = new JButton("\u9000\u51FA");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 17));
        btnNewButton_2.setBounds(376, 418, 72, 32);
        contentPane.add(btnNewButton_2);
    }
}
