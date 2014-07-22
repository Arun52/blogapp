package controllers;

import java.util.List;

import models.*;
import static play.data.Form.form;
import play.Logger;
import play.api.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.bloginfo;
import views.html.dashboard;

public class Comments extends Controller {
	public static Result index() {
		
		
		return ok(dashboard.render(Blog.find.all(), Comment.find.all(),
				User.findbyEmail(request().username())));
	}

	public static Result add(Long blog) {
		play.data.Form<Comment> comForm = form(Comment.class).bindFromRequest();
		Logger.info(""+Blog.find.ref(blog));
		Logger.info(comForm.get().text);
		Comment.create( blog, request().username(), form()
				.bindFromRequest().get("text"));
//		List<Blog> blogs=Blog.getAllBlogs();
//		List<Comment> comments=Comment.getAllComments();
		return ok(bloginfo.render(Blog.find.ref(blog),User.findbyEmail(request().username()), form(Comment.class)));
	}

}
