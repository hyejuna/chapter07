package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	//필드
	private Socket socket;
	
	//생성자
	public ServerThread(Socket socket) {
		this.socket=socket;
	}
	//메소드 g/s
	
	//메소드 일반
	
	@Override
	public void run() {
		System.out.println("[클라이언트가 연결되었습니다.]");
		
		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			//메세지 보내기
					OutputStream os = socket.getOutputStream();
					OutputStreamWriter isw = new OutputStreamWriter(os,"UTF-8");
					BufferedWriter bw = new BufferedWriter(isw);
					
			
			while(true) {
				
				String msg = br.readLine();
				
				if(msg == null) { // msg의 주소가 없을 때
					System.out.println("클라이언트 종료키 입력");
					break;
				}
				
				System.out.println("받은메세지:" +msg);
				
				bw.write(msg);
				bw.newLine();
				bw.flush();
			
			}
		
			socket.close();
			br.close();
			bw.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
