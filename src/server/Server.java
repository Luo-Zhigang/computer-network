package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args){
        //1.创建Socket对象
        ServerSocket server=null;
        //2.判断是否有客户端发送请求
        Socket client=null;
        BufferedReader br=null;

        System.out.println("服务器已开启");
        try {
            server = new ServerSocket(8080);
            client = server.accept();
            //获取来自浏览器的请求信息
            br=new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            String str=null;
            while ((str=br.readLine()).length()>0){
                System.out.println(str);
            }
            //默认显示信息
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-type:text/html");
            pw.println();
            pw.println("<h1>hello word</h1>");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //6.关闭流
            IOClose.closeAll(br,client,server);
        }

    }
}
