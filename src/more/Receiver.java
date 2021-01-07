package more;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author 罗志刚
 * @date 2020/12/28 22:08
 */
public class Receiver {
    static String ipAddr = "224.0.0.2";
    static int port = 8888;
    public static void main(String[] args) throws Exception{
        MulticastSocket socket = new MulticastSocket(port);//创建多播套接字并将其绑定到特定端口
        InetAddress address = InetAddress.getByName(ipAddr);
        socket.joinGroup(address);//只有加入组播才可以收数据，但是无需加入组播就可发数据
        //循环接收数据
        byte[] input = new byte[1024];//只允许输入512个汉字
        DatagramPacket inPacket = new DatagramPacket(input,input.length);
        new Sender(socket,address,port).start();//启动新的线程读取控制台输入
        while (true){
            socket.receive(inPacket);//阻塞在这里
            System.out.println("收到数据："+new String(input,0,inPacket.getLength()));
        }
    }
}
