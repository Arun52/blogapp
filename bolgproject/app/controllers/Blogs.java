package controllers;

import static play.data.Form.form;
import models.Blog;
import models.Comment;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;


public class Blogs extends Controller {

	public static Result index() {
		Logger.info(request().username());
		return ok(dashboard.render(Blog.find.all(), Comment.find.all(),
				User.findbyEmail(request().username())));
	}

	public static Result addblog() {
		return ok(addblog.render(form(Blog.class), User.findbyEmail(request().username())));
	}

	public static Result add() {
		Blog.create(form().bindFromRequest().get("header"),
				form().bindFromRequest().get("text"), request().username());
		return ok(dashboard.render(Blog.find.all(), Comment.find.all(),
				User.findbyEmail(request().username())));
	}
	
	public static Result bloginfo(Long blog){
		Logger.info(""+Blog.find.ref(blog)+request().username());
		return ok(bloginfo.render(Blog.find.ref(blog),User.findbyEmail(request().username()), form(Comment.class)));
	}

}