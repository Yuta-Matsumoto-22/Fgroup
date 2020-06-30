package client;

import javax.swing.*;

import transData.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.*;

public class Oserov3 extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Container c;
	JLabel l1 = new JLabel("対戦相手"+" vs "+"相手の成績");
	JLabel l2 = new JLabel("残り時間");
	JLabel l3 = new JLabel("相手の持ち時間");
	JLabel l4 = new JLabel("黒の数：白の数");
	JLabel l5 = new JLabel("あなたの番");
	JButton b1 = new JButton("設定");
	JButton b2 = new JButton("終了");
	
	
	JButton[] A = new JButton[10];
	JButton[] B = new JButton[10];
	JButton[] C = new JButton[10];
	JButton[] D = new JButton[10];
	JButton[] E = new JButton[10];
	JButton[] F = new JButton[10];
	JButton[] G = new JButton[10];
	JButton[] H = new JButton[10];
	
	JLabel chessboard ;
	JLabel chess;
	double x = 0;
	double y = 0;
	ImageIcon iconB = new ImageIcon(getClass().getResource("00Black.jpg"));
	ImageIcon iconW = new ImageIcon(getClass().getResource("00White.jpg"));
	static int turn = 0;
	Map map = new Map();
	HashMap<Integer,transData>hash = new HashMap<Integer,transData>();
	public Oserov3() {
		JFrame j = new JFrame();
		c=j.getContentPane();
		
		//j.setSize(800,600);
		j.setBounds(0,0,800,600);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane lp = new JLayeredPane();
		
		ImageIcon img = new ImageIcon(getClass().getResource("frame.jpg"));
		chessboard= new JLabel();
		chessboard.setIcon(img);
		
		img = new ImageIcon(getClass().getResource("00Black.jpg"));
		chess = new JLabel();
		chess.setIcon(img);
		
		l1.setBounds(300,10,300,20);
		chessboard.setBounds(10,40,420,420);
		chess.setBounds(28,57,43,43);
		l2.setBounds(600,50,200,30);
		l3.setBounds(600,150,200,30);
		l4.setBounds(600,250,200,30);
		l5.setBounds(50,500,100,30);
		b1.setBounds(200,500,100,30);
		b2.setBounds(380,500,100,30);
		
		for(int i=0;i<8;i++) {
			A[i]=new JButton();
			
			A[i].setBounds(28,57+i*48,48,48);
			A[i].addActionListener(this);
			A[i].setOpaque(false);A[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			B[i]=new JButton();
			
			B[i].setBounds(77,57+i*48,48,48);
			B[i].addActionListener(this);
			B[i].setOpaque(false);B[i].setContentAreaFilled(false);
		}
		
		for(int i=0;i<8;i++) {
			C[i]=new JButton();
			
			C[i].setBounds(126,57+i*48,48,48);
			C[i].addActionListener(this);
			C[i].setOpaque(false);C[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			D[i]=new JButton();
			
			D[i].setBounds(172,57+i*48,48,48);
			D[i].addActionListener(this);
			D[i].setOpaque(false);D[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			E[i]=new JButton();
			
			E[i].setBounds(220,57+i*48,48,48);
			E[i].addActionListener(this);
			E[i].setOpaque(false);E[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			F[i]=new JButton();
			
			F[i].setBounds(268,57+i*48,48,48);
			F[i].addActionListener(this);
			F[i].setOpaque(false);F[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			G[i]=new JButton();
			
			G[i].setBounds(314,57+i*48,48,48);
			G[i].addActionListener(this);
			G[i].setOpaque(false);G[i].setContentAreaFilled(false);
		}
		for(int i=0;i<8;i++) {
			H[i]=new JButton();
			
			H[i].setBounds(360,57+i*48,48,48);
			H[i].addActionListener(this);
			H[i].setOpaque(false);H[i].setContentAreaFilled(false);
		}
		

		c.add(l1);
		c.add(l2);
		c.add(l3);
		c.add(l4);
		c.add(l5);
		c.add(b1);
		c.add(b2);
		j.getLayeredPane().add(chessboard,100);
		
		for(int i=0;i<8;i++) {
			j.getLayeredPane().add(A[i],0);
			j.getLayeredPane().add(B[i],0);
			j.getLayeredPane().add(C[i],0);
			j.getLayeredPane().add(D[i],0);
			j.getLayeredPane().add(E[i],0);
			j.getLayeredPane().add(F[i],0);
			j.getLayeredPane().add(G[i],0);
			j.getLayeredPane().add(H[i],0);
		}
		c.setLayout(null);
		
		j.setVisible(true);
		j.setResizable(false);
		map.initMap();				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		if(e.getSource()==A[0]) {
			System.out.println("A[0]");
			try {
				
				Socket s = new Socket("localhost",10301);
				
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				transData data =new transData(0,0);
				//hash.put(3,data);
				oos.writeObject(data);
				oos.close();
				s.close();
				
			}catch(Exception erro) {erro.printStackTrace();}
		}
	*/
		try {
			for(int i=0; i<8;i++) {
				if(e.getSource()==A[i]) {
					map.updateMap(i, 0,turn);
					map.castToBoard(this, turn, A[i]);
					
					turn = 1-turn;
				}
			else if(e.getSource()==B[i]) {
				map.updateMap(i, 1,turn);
				map.castToBoard(this, turn, B[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==C[i]) {
				map.updateMap(i, 2,turn);
				map.castToBoard(this, turn, C[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==D[i]) {
				map.updateMap(i, 3,turn);
				map.castToBoard(this, turn, D[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==E[i]) {
				map.updateMap(i, 4,turn);
				map.castToBoard(this, turn, E[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==F[i]) {
				map.updateMap(i, 5,turn);
				map.castToBoard(this, turn, F[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==G[i]) {
				map.updateMap(i, 6,turn);
				map.castToBoard(this, turn, G[i]);
				
				turn = 1-turn;
			}
			else if(e.getSource()==H[i]) {
				map.updateMap(i, 7,turn);
				map.castToBoard(this, turn, H[i]);
				
				turn = 1-turn;
			}
			}
			/*
			if(e.getSource()==A[1]) {
				map.castToBoard(this, 1, A[1]);;
				map.updateMap(1, 0,turn);
				turn = 1-turn;
				
			}
			if(e.getSource()==A[2]) {
				
				
				map.updateMap(2, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==A[3]) {
				
				map.updateMap(3, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==A[4]) {
				
				map.updateMap(4, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==A[5]) {
				
				map.updateMap(5, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==A[6]) {
				
				map.updateMap(6, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==A[7]) {
				
				map.updateMap(7, 0,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[0]) {
				
				map.updateMap(0, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[1]) {
				
				map.updateMap(1, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[2]) {
				
				map.updateMap(2, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[3]) {
				
				map.updateMap(3, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[4]) {
				
				map.updateMap(4,1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[5]) {
				
				map.updateMap(5, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[6]) {
				
				map.updateMap(6, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==B[7]) {
				
				map.updateMap(7, 1,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[0]) {
				
				map.updateMap(0, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[1]) {
				
				map.updateMap(1, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[2]) {
				
				map.updateMap(2, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[3]) {
				
				map.updateMap(3, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[4]) {
				
				map.updateMap(4, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[5]) {
				
				map.updateMap(5, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[6]) {
				
				map.updateMap(6, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==C[7]) {
				
				map.updateMap(7, 2,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[0]) {
				
				map.updateMap(0, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[1]) {
				
				map.updateMap(1, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[2]) {
				
				map.updateMap(2, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[3]) {
				
				map.updateMap(3, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[4]) {
				
				map.updateMap(4, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[5]) {
				
				map.updateMap(5, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[6]) {
				
				map.updateMap(6, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==D[7]) {
				
				map.updateMap(7, 3,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[0]) {
				
				map.updateMap(0, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[1]) {
				
				map.updateMap(1, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[2]) {
				
				map.updateMap(2, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[3]) {
				
				map.updateMap(3, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[4]) {
				
				map.updateMap(4, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[5]) {
			
				map.updateMap(5, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[6]) {
				
				map.updateMap(6, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==E[7]) {
				
				map.updateMap(7, 4,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[0]) {
				
				map.updateMap(0, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[1]) {
				
				map.updateMap(1, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[2]) {
				
				map.updateMap(2, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[3]) {
			
				map.updateMap(3, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[4]) {
			
				map.updateMap(4, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[5]) {
				
				map.updateMap(5, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[6]) {
				
				map.updateMap(6, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==F[7]) {
				
				map.updateMap(7, 5,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[0]) {
				
				map.updateMap(0, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[1]) {
				
				map.updateMap(1, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[2]) {
				
				map.updateMap(2, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[3]) {
				
				map.updateMap(3, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[4]) {
				
				map.updateMap(3, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[5]) {
				
				map.updateMap(5, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[6]) {
			
				map.updateMap(6, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==G[7]) {
			
				map.updateMap(7, 6,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[0]) {
				
				map.updateMap(0, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[1]) {
			
				map.updateMap(1, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[2]) {
				
				map.updateMap(2, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[3]) {
				
				map.updateMap(3, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[4]) {
				
				map.updateMap(4, 7,turn);
				turn = 1-turn;
				System.out.println("h4");
			}
			if(e.getSource()==H[5]) {
				
				map.updateMap(5, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[6]) {
				
				map.updateMap(6, 7,turn);
				turn = 1-turn;
			}
			if(e.getSource()==H[7]) {
				
				map.updateMap(7, 7,turn);
				turn = 1-turn;
			}
			*/

		}catch(Exception erro) {
			erro.printStackTrace();
		}
	
	}
	

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Oserov3();
		
	}

	public class Map {
		int r;
		int l;
		int [][]map = new int [8][8];
		public Map(){
			this.r = 8;
			this.l = 8;
			}
				
		public void initMap() {
			for(int i=0;i<8;i++) {
				Arrays.fill(map[i], 2);
			}	
		}
		
		public void printMap() {
			System.out.println("----------------");
			for(int i=0; i<8;i++) {
				for(int j=0; j<8; j++) {
					System.out.print(map[i][j]);
					System.out.print(' ');
				}System.out.println();
			}	
			
		}
	//通过调整boundrow / boundline 来调整棋盘大小，r是行，l是列
		
		public void updateMap(int r, int l,int p) {
		
			if(map[r][l]!=2) {		
				System.out.println("this place has been occupied");	}
			else {
			map[r][l]=p;
			//searching across single line
			int boundrow = 4;
			int boundline = 4;
			int right,left,up,down;
			int tempr, templ;
			right =l;
			left =l;
			up = r;
			down = r;
			//use for moving target
			templ=l;
			tempr=r;
			
	//Going left		
			while(templ-1>=0) {
				if(map[r][templ-1]==p) {
					left = templ-1;
					break;
				}
				else {
					templ= templ-1;
					
				}
			}
	//Going right		
			while(templ+1<=boundline) {
				if(map[r][templ+1]==p) {
					right= templ+1;
					break;
				}
				else {
					templ = templ+1;
				}
				
			}
	//turn them around			
			for(int i=left;i<=right;i++) {
				map[r][i]=p;
			}

	//Going up
			while(tempr-1>=0) {
				if(map[tempr-1][l]==p) {
					up = tempr-1;
					break;
				}
				else {
					tempr= tempr-1;
					
				}
			}		

	//Going down
			while(tempr+1<=boundrow) {
				if(map[tempr+1][l]==p) {
					down = tempr+1;
					break;
				}
				else {
					tempr = tempr+1;
				}
				
			}

	//turn them around agian		
			for(int i=up;i<=down;i++) {
				map[i][l]=p;
			}
	//print map
		printMap();
		
		
			}//else 
		} //updateMap
	public void castToBoard(Oserov3 osero, int p,JButton b) {
		if(p==1) {
			b.setIcon(iconW);
			b.setOpaque(true);
		}
		else if(p==0) {
			b.setIcon(iconB);
			b.setOpaque(true);
		}
		else {
			
		}
		for(int i=0; i<8;i++) {
			if(i==0) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.A[j].setIcon(iconW);
				        osero.A[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.A[j].setIcon(iconB);
				        osero.A[j].setOpaque(true);
					}
				}
			}
			else if(i==1) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.B[j].setIcon(iconW);
				        osero.B[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.B[j].setIcon(iconB);
				        osero.B[j].setOpaque(true);
					}
				}
			}
			else if(i==2) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.C[j].setIcon(iconW);
				        osero.C[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.C[j].setIcon(iconB);
				        osero.C[j].setOpaque(true);
					}
				}
			}
			else if(i==3) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.D[j].setIcon(iconW);
				        osero.D[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.D[j].setIcon(iconB);
				        osero.D[j].setOpaque(true);
					}
				}
			}
			else if(i==4) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.E[j].setIcon(iconW);
				        osero.E[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.E[j].setIcon(iconB);
				        osero.E[j].setOpaque(true);
					}
				}
			}
			else if(i==5) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.F[j].setIcon(iconW);
				        osero.F[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.F[j].setIcon(iconB);
				        osero.F[j].setOpaque(true);
					}
				}
			}
			else if(i==6) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.G[j].setIcon(iconW);
				        osero.G[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.G[j].setIcon(iconB);
				        osero.G[j].setOpaque(true);
					}
				}
			}
			else if(i==7) {
				for(int j=0;j<8;j++) {
					if(map[j][i]==1) {
				        osero.H[j].setIcon(iconW);
				        osero.H[j].setOpaque(true);
					}
					else if(map[j][i]==0) {
						osero.H[j].setIcon(iconB);
				        osero.H[j].setOpaque(true);
					}
				}
			}
		}//for
		
		
	
	}//castToBoard 将棋子投射到棋盘上
  } //Class Map
	
} //Oserov2
	

// comment: 棋盘应该是横纵为偶数，并且初始化双方各两个子交叉排列在中心，只有旁边有棋子的时候才能下棋
			

/*映射机制：按钮映射到Map的实例上，Map的实例将按钮的状态（黑或者白）映射到棋盘上
    协议机制：Osero传送Object数据，根据Object的类型（isinstanceof()）来判断是什么数据然后读取数据
  */
 //(GUI)button(send chess loction to the sever) - map -updata - map - GUI
//GUI(Login Room Osero) - client - sever - client - GUI(Login Room Osero)
