package controllers;

import static play.data.Form.form;
import models.Blog;
import models.Comment;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import controllers.Application.Addblog;

public class Blogs extends Controller{

	public static Result index(){
		return ok(
				dashboard.render(
						Blog.find.all(),
						Comment.find.all(),
						request().username(),
						form(Blog.class)));
	}
	
	public static Result addblog(){
		return ok(addblog.render(form(Addblog.class),request().username()));
	}
	
	public static Result add(){
		Blog newBlog=Blog.create(form().bindFromRequest().get("header"),
				form().bindFromRequest().get("text"), request().username());
		return ok(
				dashboard.render(
						Blog.find.all(),
						Comment.find.all(),
						request().username(),
						form(Blog.class)));
	}
	
}