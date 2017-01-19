package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "172.16.26.223";
	private static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		
		//1.키보드 연결
		Scanner sc = new Scanner(System.in);
		Socket socket = null;

		try {
			//2.socket 생성
			socket = new Socket();
			
			//3. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			//4. IOStream 세팅
			BufferedReader br=
					new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw=
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while (true) {
				System.out.println(">>");
				String message = sc.nextLine();
				
				if("exit".equals(message)){
					break;
				}
				
				//5. 메세지 보내기
				pw.println(message);
				
				//6. 에코 메세지 받기
				String echoMessage = br.readLine();
				if(echoMessage == null){
					//
					System.out.println("[client] disconnected by server");
					break;
				}
				//7. 출력
				System.out.println("<<");

			}
		} catch (ConnectException e){
			System.out.println("[client] not connected to server ");
		} catch (SocketException e){
			System.out.println("[client] closed by server");
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally{
			
			try {
				if(socket != null && socket.isClosed() == false){
				socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sc.close();
		}
	}

}
