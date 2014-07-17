package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@OneToMany
	public List<Comment> comments;

	@ManyToOne
	public User user;

	public static Model.Finder<Long, Blog> find = new Model.Finder<Long, Blog>(
			Long.class, Blog.class);

	public static List<Blog> getAllBlogs() {

		List<Blog> blogs = new ArrayList<Blog>();
		blogs = find.fetch("order by created_at DESC").setMaxRows(10)
				.findList();

		return blogs;
	}

}
