package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
	
	private static final String SERVER_IP="172.16.26.223";
	private static final int SERVER_PORT= 5000;
	
	public static void main(String[] args) {
		Socket socket= null;
		
		try {
			//1. socket생성
			socket= new Socket();
			
			//2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			//3. IOStream 받아오기
			InputStream is= socket.getInputStream();
			OutputStream os= socket.getOutputStream();
			
			//4. 쓰기 / 읽기
			String data ="hello\n";
			
			os.write(data.getBytes("utf-8"));
			
			byte[] buffer = new byte[255];
			int readByteCount= is.read(buffer); // blocking
			
			if(readByteCount <= -1){
				System.out.println("[client] disconnectd by server");
				return;
			}
			
			data = new String(buffer,0,readByteCount,"utf-8");
			System.out.println("[client] received " +data);
			
		} catch (ConnectException e){
			System.out.println("[client] not connected to server ");
		} catch (SocketException e){
			System.out.println("[client] closed by server");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(socket !=null && socket.isClosed() ==false)
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
