package client;
import java.net.Socket;

import transData.*;

import java.io.*;
public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message = "Initial answer";
		try {
			transData user = new transData(10);
			transData answer = new transData(11);
			user.set_login_name_pass("usr_1","pass_1");
			Socket s = new Socket("localhost",10100);
			OutputStream os = s.getOutputStream();
			InputStream is =s.getInputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(user);
			oos.flush();
			s.shutdownOutput();
	
			
			ObjectInputStream ois = new ObjectInputStream(is);
			answer = (transData)ois.readObject(); 
			message = answer.get_login_answer();
			System.out.println(message);
			s.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
