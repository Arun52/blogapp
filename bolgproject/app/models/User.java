package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public static Long id;

	public String name;
	public String password;
	public String email;

	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;

	}

	public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(
			Long.class, User.class);

	public static List<User> findAll() {
		return find.all();
	}
	
	public static User findbyEmail(String email){
		return find.where()
				.eq("email", email).findUnique();
	}
	
	public static User authanticate(String email,String password){
		return find.where()
				.eq("email", email)
				.eq("password", password)
				.findUnique();
	}
	
	

}
