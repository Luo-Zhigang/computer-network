package ping;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;

public class Frame extends JFrame {

    private JTextField textField;
    private JTextField textField_1;

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
        setBounds(100, 100, 540, 428);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("宋体", Font.BOLD, 16));
        textField.setBounds(77, 34, 132, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("宋体", Font.BOLD, 16));
        textField_1.setColumns(10);
        textField_1.setBounds(310, 34, 132, 26);
        contentPane.add(textField_1);

        JLabel lblNewLabel = new JLabel("   \u2014\u2014\u2014\u2014\u2014\u2014\uFF1E");
        lblNewLabel.setBounds(205, 37, 138, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u8D77\u59CBIP");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_1.setBounds(22, 33, 58, 26);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("\u7ED3\u675FIP");
        lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(445, 33, 58, 26);
        contentPane.add(lblNewLabel_1_1);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("宋体", Font.BOLD, 17));
        textArea.setBounds(22, 84, 481, 210);
        contentPane.add(textArea);

        JButton btnNewButton = new JButton("\u6E05\u9664");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //清除
                textField.setText("");
                textField_1.setText("");
                textArea.setText("");
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton.setBounds(22, 331, 68, 35);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u8FD0\u884C");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //运行
                String text_1 = textField.getText();
                String text_2 = textField_1.getText();
                String[] split_1 = text_1.split("\\.");
                String[] split_2 = text_2.split("\\.");
                StringBuffer line = new StringBuffer("");
                if (text_1.equals("")||text_2.equals("")){
                    JOptionPane.showMessageDialog(null, "起始和结束IP为空，请重新输入", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        for (int i = Integer.parseInt(split_1[split_1.length - 1]); i <= Integer.parseInt(split_2[split_2.length - 1]); i++) {
                            String ip = text_1.substring(0, text_1.length() - split_1[split_1.length - 1].length()) + i;
                            InetAddress inetAddress = InetAddress.getByName(ip);
                            boolean status = inetAddress.isReachable(3000);
                            if (status) {
                                line.append(ip).append("      在线   ").append("主机：").append(inetAddress.getHostName()).append("\n");
                            } else {
                                line.append(ip).append("      不在线").append("\n");
                            }
                        }
                        textArea.append(String.valueOf(line));
                    } catch (Exception ev) {
                        System.out.println(ev.getMessage());
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_1.setBounds(241, 331, 68, 35);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u9000\u51FA");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //退出
                System.exit(0);
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_2.setBounds(440, 331, 68, 35);
        contentPane.add(btnNewButton_2);
    }
}
