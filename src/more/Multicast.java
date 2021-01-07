package more;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Multicast extends JFrame implements Runnable {

    private static TextArea mShowMsg; // 显示信息记录
    private TextField mMsgField; // 输入发送内容组件
    private JButton mSendButton; // 发送按钮
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    protected static final int PORT = 3838; // 端口
    private static final String BROADCAST_IP = "230.0.0.1";
    // IP协议为多点广播提供了一些特殊的IP地址，在224.0.0.0到239.255.255之间
    private static MulticastSocket multicast; // 多播套接字
    private static InetAddress group;
    private static boolean isFirst; // 是否信息记录里的第一条信息

    public Multicast() {
        setTitle("多播群聊室");
        setSize(450, 400);
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setLocation(width / 2 - getWidth() / 2, height / 2 - getHeight() / 2);
        setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    sendMessage("我下线了！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

        mShowMsg = new TextArea();
        mShowMsg.setBounds(20, 10, 400, 280);
        mShowMsg.setEditable(false);
        add(mShowMsg);
        mMsgField = new TextField();
        mMsgField.setBounds(20, 310, 320, 28);
        add(mMsgField);
        mSendButton = new JButton("发送");
        mSendButton.setBounds(350, 310, 70, 28);
        mSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 发送数据
                try {
                    sendMessage(mMsgField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        add(mSendButton);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        isFirst = false;
        multicast = new MulticastSocket(PORT);
        group = InetAddress.getByName(BROADCAST_IP); // 多播组
        multicast.joinGroup(group); // 加入该多播组,才能接收到数据包
        new Thread(new Multicast()).start();
        try {
            Thread.sleep(100); // 等信息接收者做好准备,在发送“我上线了！”的信息,保证能接收到
            sendMessage("我上线了!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播此条信息
     *
     *
     */
    private static void sendMessage(String msg) throws IOException {
        // 被发送的数据包类
        DatagramPacket packet = new DatagramPacket(msg.getBytes(),
                msg.getBytes().length, group, PORT);
        multicast.send(packet); // 发送数据包

    }

    @Override
    public void run() {
        // 接收信息
        byte[] msg = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(msg, msg.length);
            try {
                multicast.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isFirst) {
                mShowMsg.append("\r\n");
            }
            System.out.println(new String(msg, 0, packet.getLength()));
            isFirst = true;
            mShowMsg.append(packet.getAddress() + " 说： "
                    + new String(msg, 0, packet.getLength()).trim());
        }

    }
}