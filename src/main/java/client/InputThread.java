package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class InputThread extends Thread{

  Socket sock;
  public InputThread(Socket socket) {
    this.sock= socket;
    start();
  }

  @Override
  public void run() {
    while(true){
      // 서버로부터 데이터를 받기 위한 InputStream
      try {
        InputStream in = sock.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        // 서버로부터 데이터 수신
        String receivedData = dis.readUTF();
        System.out.println("서버로부터 받은 데이터: " + receivedData);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }
  }
}
