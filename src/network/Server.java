package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ author 罗志刚
 * @ date 2020/12/28 10:39
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //建立tcp的服务端
        ServerSocket serverSocket = new ServerSocket(9090);
        //接受客户端的连接，产生一个Socket
        Socket socket = serverSocket.accept();
        //获取到Socket的输入流对象
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //获取到Socket输出流对象
        OutputStreamWriter socketOut =  new OutputStreamWriter(socket.getOutputStream());
        //获取键盘的输入流对象
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
        //读取客户端的数据
        String line = null;
        while((line = socketReader.readLine())!=null){
            System.out.println("客户端："+ line);
            System.out.print("服务端：");
            line = keyReader.readLine();
            socketOut.write(line+"\r\n");
            socketOut.flush();
        }
        serverSocket.close();
    }
}
