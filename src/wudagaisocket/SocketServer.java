package wudagaisocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) {
        Socket client=null;
        ServerSocket server=null;
        /**
         * 使用处理流来发送和读取数据，也就是聊天
         */
        try {
            //实例化一个服务器
            server =new ServerSocket(5008);

            //等待来自网络的请求
            //这个阻塞方法会返回客户端的套接字实例
            //如果没有客户端连接过来，那么该方法一直阻塞
            client = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(PrintWriter pr=new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()))
                ){
                while (true) {
                    //先输出再读取
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.nextLine();
                    pr.println(message);
                    //接受客户端信息
                    String clientmessage = bf.readLine();
                    System.out.println(clientmessage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
