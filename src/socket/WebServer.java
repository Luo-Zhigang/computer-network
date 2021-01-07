package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 罗志刚
 * @date 2020/12/30 13:26
 */
//创建一个ServerSocket对象；
//调用ServerSocket对象的accept方法，等待连接，连接成功会返回一个Socket对象，否则一直阻塞等待；
//从Socket对象中获取InputStream和OutputStream字节流，这两个流分别对应request请求和response响应；
//处理请求：读取InputStream字节流信息，转成字符串形式，并解析，这里的解析比较简单，仅仅获取uri(统一资源标识符)信息;
//处理响应：根据解析出来的uri信息，从WEB_ROOT目录中寻找请求的资源资源文件, 读取资源文件，并将其写入到OutputStream字节流中；
//关闭Socket对象；
public class WebServer {
    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                //服务器套接字
                Socket socket = serverSocket.accept();
                new HttpServer(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
