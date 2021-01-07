package more;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * @author 罗志刚
 * @date 2020/12/28 22:09
 */
public class Sender extends Thread{
    MulticastSocket socket;
    InetAddress address;
    int port;

    public Sender(MulticastSocket socket,InetAddress address,int port){
        this.socket = socket;
        this.address = address;
        this.port = port;
    }
    @Override
    public void run(){
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String info = scanner.nextLine();
                byte[] output = info.getBytes();
                socket.send(new DatagramPacket(output, output.length, address, port));//发送出去的datagram必须包含全部地址和端口信息，因为无连接的缘故
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}