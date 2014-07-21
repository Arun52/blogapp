package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class Comment extends Model{
	@Id
	public Long Id;
	
	public String text;
	
	@ManyToOne
	public User user;
	
	@ManyToOne
	public Blog blog;
	
	
	public static Model.Finder<Long, Comment> find=new Model.Finder<Long, Comment>(
			Long.class,Comment.class);
		
	public static List<Comment> getAllComments(){
		List<Comment> comments=Comment.find.all();
		return comments;
	}
	
	public static List<Comment> findCommentsByBlog(Long blog){
		return Comment.find.where()
				.eq("blog.id",blog)
				.findList();
	}
	
	public static Comment create(Long blog,String user,String text){
		Comment comment=new Comment();
		comment.blog=Blog.find.ref(blog);
		comment.user=User.findbyEmail(user);
		comment.text=text;
		comment.save();
		return comment;
		
	}
	
}