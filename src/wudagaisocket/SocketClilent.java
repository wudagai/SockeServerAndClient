package wudagaisocket;


import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class SocketClilent {
    public static void main(String[] args) {
        //创建一个客户端的连接请求
        Socket client=null;
        try {
            client=new Socket("localhost",5008);
           SocketAddress address= client.getLocalSocketAddress();
            System.out.println(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //客户端读取服务端发送过来的信息
        try(PrintWriter pr=new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()))
        ){
        while (true) {
            //客户端读取服务端发送过来的信息
            String message = bf.readLine();
            System.out.println(message);
            //给服务端发送信息
            Scanner scanner = new Scanner(System.in);
            String writermessage = scanner.nextLine();
            pr.println(writermessage);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
