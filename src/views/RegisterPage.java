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

public class RegisterPage extends JFrame {
	private JPanel northPanel, centerPanel, southPanel;
	private JLabel lblTitle, lblName, lblEmail, lblPassword, lblPhone, lblConfirm, lblLogin;
	private JTextField txtName, txtEmail, txtPhone;
	private JPasswordField passwordField, confirmField;
	private JButton btnRegister;
	
	private DatabaseConnection con = DatabaseConnection.getInstance();

	public RegisterPage() {
		initialize();
		setVisible(true);
		setSize(new Dimension(600, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String email = txtEmail.getText();
				String phone = txtPhone.getText();
				String password = passwordField.getText();
				String confirm = confirmField.getText();
				
				if(!AuthFacade.getInstance().register(name, email, password, confirm, phone)) {
					JOptionPane.showMessageDialog(null, AuthFacade.getInstance().getErrorMsg());
				} else {
					JOptionPane.showMessageDialog(null, "Register success! You will be redirected to Login Page.");
					new LoginPage();
					dispose();
				}
			}
		});
	
		
		lblLogin.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				new LoginPage();
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
		centerPanel = new JPanel(new GridLayout(5, 2, 0, 10));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
		southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 50, 30));
		
		//label
		lblTitle = new JLabel("Register");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblName = new JLabel("Name");
		lblEmail = new JLabel("Email");
		lblPassword = new JLabel("Password");
		lblConfirm = new JLabel("Confirm Password");
		lblPhone = new JLabel("Phone");
		lblLogin = new JLabel("Already have an account?");
		lblLogin.setHorizontalAlignment(JLabel.CENTER);
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		//text
		txtName = new JTextField();
		txtEmail = new JTextField();
		txtPhone = new JTextField();
		passwordField = new JPasswordField();
		confirmField = new JPasswordField();
		
		//button
		btnRegister = new JButton("Register");
		
		northPanel.add(lblTitle);
		
		centerPanel.add(lblName);
		centerPanel.add(txtName);
		centerPanel.add(lblEmail);
		centerPanel.add(txtEmail);
		centerPanel.add(lblPhone);
		centerPanel.add(txtPhone);
		centerPanel.add(lblPassword);
		centerPanel.add(passwordField);
		centerPanel.add(lblConfirm);
		centerPanel.add(confirmField);
		southPanel.add(btnRegister);
		southPanel.add(lblLogin);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
}
