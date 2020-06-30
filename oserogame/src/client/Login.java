package client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import user.*;
	
class Point{
	int x;
	int y;
	public Point() {
		
	}
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	int getX() {
		return this.x;
	}
	int getY() {
		return this.y;
	}
	void setX(int x) {
		this.x = x;
	}
	void setY(int y) {
		this.y = y;
	}
	Point getPoint() {
		return this;
	}
} 

//When using MouseListener interface, it has to implement five methods 
public class Login extends JFrame implements ActionListener,MouseListener{
	/**
	 * 
	 */
		
	private static final long serialVersionUID = 123456789;
	Container c;
	JTextField jt1;
	JPasswordField jp1;
	JButton jb1,jb2,jb3;
	String playername;
	String password;
	JLabel bg;
	HashMap<String,User>hash = new HashMap<String,User>();
	User user;
	Point point = new Point();
	int x=0,y=0;
	public Login() {
		JFrame j = new JFrame();
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//j.setResizable(false);
		j.setSize(300,400);
		c = j.getContentPane();
		c.setLayout(null);

//set background
		bg = new JLabel();
		ImageIcon img = new ImageIcon(getClass().getResource("bg.jpg"));
		bg.setIcon(img);
		bg.setBounds(0,0,300,400);
		
		
					
		jt1 = new JTextField("Player name ", 11);
		jp1 = new JPasswordField("Password", 11);
		jt1.setBounds(70,50,150,20);
		jp1.setBounds(70,100,150,20);
		
		jb1 = new JButton("Register");
		jb2 = new JButton("Login");
		jb3 = new JButton("Change Password");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb1.setBounds(50,150,90,20);
		jb2.setBounds(150,150,90,20);
		jb3.setBounds(70,200,150,20);
		
		c.add(bg);
		c.add(jt1);
		c.add(jp1);
		c.add(jb1);
		c.add(jb2);
		c.add(jb3);	

		
		j.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jb1) {
			playername = jt1.getText();
			password = String.valueOf(jp1.getPassword());
			try{
				Socket socket = new Socket("localhost",10000);
				
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				data = new User(password,playername);
				hash.put("user1",user);
				oos.writeObject(hash.get("user1"));
				oos.close();
				
				socket.close();
				
				
			}catch(Exception erro) {
				erro.printStackTrace();
			}
		}
		if(e.getSource() == jb2) {
			
			
			
			//playername = jt1.getText();
			//password = String.valueOf(jp1.getPassword());
			//System.out.println(playername);
			//System.out.println(password);
			
		}
		if(e.getSource() == jb3) {
			playername = jt1.getText();
			password = String.valueOf(jp1.getPassword());
			System.out.println(playername);
			System.out.println(password);
		}
	}
	
	public void mouseReleased(MouseEvent e) {};//鼠标按键被释放是被触发
	 
    public void mousePressed(MouseEvent e) {};//鼠标按键被按下时被触发
 
    public void mouseExited(MouseEvent e) {};//光标移除组件时被触发
 
    public void mouseEntered(MouseEvent e) {};//光标移入组件时被触发
 
    public void mouseClicked(MouseEvent e) {
    	if(e.getSource() == bg) {
    		System.out.println("Clicking BG");
    	}
    };//发生单击事件时被触发

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		new Login();
	}
	
}

