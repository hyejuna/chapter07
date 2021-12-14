package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException{

		Socket socket = new Socket(); // 종이컵 전화기 한쪽 종이컵
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=============================");
		System.out.println("[서버에 연결을 요청합니다.]");
		
		socket.connect(new InetSocketAddress("192.168.0.52", 10001)); // 종이컵 연결 시도
		
		System.out.println("[서버에 연결되었습니다.]");
		
		//메세지 보내기
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		// Scanner sc = new Scanner(System.in); -> Scanner 단순하게 만들어보기
		
		InputStream in = System.in; //System이라는 static class에서 in이라는 field.
		InputStreamReader sisr = new InputStreamReader(in);
		BufferedReader sbr = new BufferedReader(sisr);
		
		
		
		while(true) {
			//String str = sc.nextLine();
			String str = sbr.readLine();
			
			if("/q".equals(str)) { // nullpoint 예외 발생 대비. str.equals는 str 주소 없는 null 발생할수도 있어서...
				System.out.println("종료키 입력");
				break;
			}
			
			bw.write("[나혜주]" + str);
			bw.newLine();
			bw.flush();
			
			
			String reMsg = br.readLine();
			System.out.println("Server:[" +reMsg + "]");
			
			
		}
		
		System.out.println("============================");
		
		//System.out.println("클라이언트 종료");	println 단순하게 만들기
		OutputStream out = System.out;
		OutputStreamWriter posr = new OutputStreamWriter(out);
		BufferedWriter pbw = new BufferedWriter(posr);
		
		pbw.write("<클라이언트종료> 스트림 사용 구현");
		pbw.newLine();
		pbw.flush();
		
		//sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
