package controllers;

import static play.data.Form.form;

import java.util.Date;
import java.util.List;

import models.Blog;
import models.Comment;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		List<Blog> blogs = Blog.getAllBlogs();
		List<Comment> comments=Comment.getAllComments();
		return ok(dashboard.render(blogs ,comments,User.findbyEmail(request().username()), form(Comment.class)));
	}

	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}

	}

	/**
	 * Login page.
	 */
	public static Result login() {
		return ok(login.render(form(Login.class)));
	}

	/**
	 * Handle login form submission.
	 */
	public static Result authenticate() {

		Form<Login> loginForm = form(Login.class).bindFromRequest();
		Logger.info(loginForm.get().email);
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(routes.Application.index());
		}
	}

	/**
	 * ; Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}

//	public static class Addblog {
//		
//		public String header;
//		public String text;
//		
//	}
	
//	public static Result addblog(){
//		return ok(addblog.render(form(Addblog.class)));
//	}
//	
//	public static Result blogadd(){
//		Form<Addblog> BlogForm=form(Addblog.class).bindFromRequest();
//		Addblog addBlog=BlogForm.get();
//		Logger.info(addBlog.header);
//		Logger.info(addBlog.text);
//		Blog newBlog=new Blog();
//		newBlog.header=BlogForm.get().header;
//		newBlog.text=BlogForm.get().text;
//		newBlog.created_at=new Date();
//		newBlog.save();
//		return redirect(routes.Application.index());
//	}
//	
//	public static Result addComment(){
//		Form<Comment> comForm=form(Comment.class).bindFromRequest();
//		Logger.info(comForm.get().text);
//		Comment newComment=comForm.get();
//		newComment.blog = comForm.get().blog;
//		newComment.save();
//		return redirect(routes.Application.index());
//	}
//	
}
