package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Blog extends Model {

	@Id
	public Long id;

	public String text;

	public Date created_at;

	public String header;
	
	@ManyToOne
	public User user;

	@OneToMany(cascade=CascadeType.ALL)
	public List<Comment> comments;
	

	public Blog(String header,String text,User user){
		this.header=header;
		this.text=text;
		this.user=user;
	}

	public static Model.Finder<Long, Blog> find = new Model.Finder<Long, Blog>(
			Long.class, Blog.class);

	public static List<Blog> getAllBlogs() {

		List<Blog> blogs = new ArrayList<Blog>();
		blogs=Blog.find.all();
		//blogs = find.fetch("order by created_at DESC").setMaxRows(10)
			//	.findList();

		return blogs;
	}
		
	public static Blog findUnique(String header,String text){
		return find.where().
				eq("header", header)
				.eq("text", text).findUnique();
	}
	
	public static List<Blog> findInvolving(String user){
		return find.where()
				.eq("user.email", user)
				.findList();
	}
	
	public static Blog create(String header,String text,String user){
		Blog blog=new Blog(header,text,User.findbyEmail(user));
		blog.save();
		return blog;
	}
}

