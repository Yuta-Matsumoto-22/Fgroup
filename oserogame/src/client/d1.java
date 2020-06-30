import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class d1 extends JFrame implements ActionListener{
	static final long serialVersionUID = 1;
	JLabel label[] = new JLabel[2];
	JTextField txt = new JTextField();
	JPasswordField pwd = new JPasswordField();
	JButton btn[] = new JButton[4];

	public d1(String title){
		super(title);
		JPanel p = (JPanel)getContentPane();
		p.setLayout(null);
		label[0] = new JLabel("プレイヤ名");
		label[1] = new JLabel("パスワード");
		btn[0] = new JButton("新規登録");
		btn[1] = new JButton("ログイン");
		btn[2] = new JButton("パスワード");
		btn[3] = new JButton("ルール説明");

		label[0].setBounds(10, 10, 75, 20);
		label[1].setBounds(10, 30, 75, 20);
		txt.setBounds(90, 10, 120, 20);
		pwd.setBounds(90, 30, 120, 20);
		btn[0].setBounds(15, 60, 100, 20);
		btn[1].setBounds(125, 60, 100, 20);
		btn[2].setBounds(40, 90, 150, 20);
		btn[3].setBounds(40, 120, 150, 20);

		p.add(label[0]);
		p.add(label[1]);
		p.add(txt);
		p.add(pwd);
		p.add(btn[0]);
		p.add(btn[1]);
		p.add(btn[2]);
		p.add(btn[3]);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 250);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//onclick
	}

	public static void main(String[] args) {
		new d1("Display-1");
	}
}