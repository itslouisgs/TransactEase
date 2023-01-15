package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DatabaseConnection;
import facades.AuthFacade;
import session.LoggedInUser;

public class LoginPage extends JFrame {
	private JPanel northPanel, centerPanel, southPanel;
	private JLabel lblLogin, lblEmail, lblPassword, lblRegister;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	
	private DatabaseConnection con = DatabaseConnection.getInstance();

	public LoginPage() {
		initialize();
		setVisible(true);
		setSize(new Dimension(500, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String email = emailTextField.getText();
				String password = passwordField.getText();
				
				if(!AuthFacade.getInstance().login(email, password)) {
					JOptionPane.showMessageDialog(null, AuthFacade.getInstance().getErrorMsg());
				} else {
					if(LoggedInUser.getInstance().getLogged().getRole().equals("admin")) {
						new AdminPage();
						dispose();
					}
				}
			}
		});
	
		lblRegister.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				new RegisterPage();
				dispose();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private void initialize() {
		northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		centerPanel = new JPanel(new GridLayout(2, 2, 0, 10));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
		southPanel = new JPanel(new GridLayout(3, 1));
		southPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 50, 20));
		
		//label
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblEmail = new JLabel("Email");
		lblPassword = new JLabel("Password");
		lblRegister = new JLabel("Don't have an account?");
		lblRegister.setHorizontalAlignment(JLabel.CENTER);
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		//text
		emailTextField = new JTextField();
		passwordField = new JPasswordField();
		
		//button
		btnLogin = new JButton("Login");
		
		northPanel.add(lblLogin);
		centerPanel.add(lblEmail);
		centerPanel.add(emailTextField);
		centerPanel.add(lblPassword);
		centerPanel.add(passwordField);
		southPanel.add(btnLogin);
		southPanel.add(lblRegister);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
}
