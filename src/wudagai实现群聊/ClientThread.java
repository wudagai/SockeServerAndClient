package wudagai实现群聊;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket client;
    public ClientThread(Socket client){
        this.client=client;
    }

    @Override
    public void run() {
        //不断接收服务端转发的信息
        try(BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()))){
            String message=null;
            while (true) {
                message = bf.readLine();
                System.out.println(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
