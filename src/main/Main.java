package main;

import java.util.Scanner;

import facades.AuthFacade;
import models.users.Customer;
import models.users.User;

public class Main {
	Scanner scan = new Scanner(System.in);

	public Main() {
		User user = null;
		int menu = 0;
		
		do {
			do {
				System.out.println("NAMA_APP");
				System.out.println("========");
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Login as Guest");
				System.out.println("99. Exit");
				System.out.print(">> ");
				
				try {
					menu = scan.nextInt();
				} catch (Exception e) {
					menu = 0;
				}
				
				scan.nextLine();
				
				if (menu == 1) {
					user = register();
				} else if (menu == 2) {
					user = login();
				} else if (menu == 3) {
					user = AuthFacade.loginAsGuest();
				} else if (menu == 99) {
					System.exit(0);
				}
				
			} while (user == null);
			
			int option = 0;
			
			do {
				user.displayInformation();
				
				System.out.println("Main Menu");
				System.out.println("=========");
				if (user.getRole().equalsIgnoreCase("customer")) {
					System.out.println("1. Order");
					System.out.println("2. Messages");
				}
				if (user.getRole().equalsIgnoreCase("admin")) {
					System.out.println("1. Insert New Product");
					System.out.println("2. Manage Products");
				}
				System.out.println("0. Logout");
				System.out.print(">> ");
				
				try {
					option = scan.nextInt();
				} catch (Exception e) {
					option = -1;
				}
				
				scan.nextLine();
				
				if (option == 1) {
					// order
				} else if (option == 2  && user.getRole().equalsIgnoreCase("customer")) {
					// messages pake observer
				} else if (option == 2 && user.getRole().equalsIgnoreCase("admin")) {
					// manage products (admin only)
					
					// insert pake builder kali ye(?)
					
				} else if (option == 0) {
					user = AuthFacade.logout();
				}
				
			} while (option != 0);
			
		} while (true);
	}

	private User login() {
		String email = "", password = "";
		
		do {
			System.out.print("Enter your email [eg: example@domain.com]: ");
			email = scan.nextLine();
		} while (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"));
		
		do {
			System.out.print("Enter your password [must be alphanumeric]: ");
			password = scan.nextLine();
		} while (!password.matches("^[a-zA-Z0-9]*$"));
		
		return AuthFacade.login(email, password);
	}

	private Customer register() {
		String name = "", email = "", password = "", phone = "";
		
		do {
			System.out.print("Enter your name [must be filled]: ");
			name = scan.nextLine();
		} while (name.isBlank());

		do {
			System.out.print("Enter your email [eg: example@domain.com]: ");
			email = scan.nextLine();
		} while (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"));
		
		do {
			System.out.print("Enter your password [must be alphanumeric]: ");
			password = scan.nextLine();
		} while (!password.matches("^[a-zA-Z0-9]*$"));
		
		do {
			System.out.print("Enter your phone [must contain 12 digits and must be numeric]: ");
			phone = scan.nextLine();
		} while (phone.length() != 12 || !phone.matches("^[0-9]*$"));
		
		return (Customer) AuthFacade.register(name, email, password, phone);
	}

	public static void main(String[] args) {
		new Main();
	}

}
