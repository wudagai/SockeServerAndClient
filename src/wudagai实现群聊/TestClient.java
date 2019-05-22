package wudagai实现群聊;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    /*
    需求：客户端至少两个线程  一个是接收信息  一个是发信息
     */
    public static void main(String[] args) {
        Socket client=null;
        try {
            client=new Socket("localhost",5067);
            //开辟一个线程不断接收服务端转发的信息
            new Thread(new ClientThread(client)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(PrintWriter pr=new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true)){
           //发信息
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                pr.println(message);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
