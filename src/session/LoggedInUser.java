package session;

import models.users.User;

public class LoggedInUser {
	public static LoggedInUser instance;
	private User logged;

	public static LoggedInUser getInstance() {
		if(instance == null) {
			instance = new LoggedInUser();
		}
		
		return instance;
	}

	public User getLogged() {
		return logged;
	}

	public void setLogged(User logged) {
		this.logged = logged;
	}
	
	public void removeLogged() {
		this.logged = null;
	}

}
