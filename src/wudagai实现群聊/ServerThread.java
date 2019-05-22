package wudagai实现群聊;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements  Runnable{
    private Socket client;
    public ServerThread(Socket client){
        this.client=client;
    }
    @Override
    public void run() {
        //一直不停地接收客户端发来的信息然后转发至其他客户端
        try( BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()))){
            String message=null;
            while (true) {
                 message= bf.readLine();//接收客户端发送的信息
                //转发消息 由当前获取得到的套接字转发,
                System.out.println(message);
                for (Socket socket : TestServer.sockets) {
                    //如果是自己发的信息，不用转发回自己
                    if (client == socket) {
                        continue;
                    }
                    PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    //转发的都是当前client的消息
                    pr.println(message);
                }
            }

        }catch (Exception e){
                e.printStackTrace();
        }
    }
}
