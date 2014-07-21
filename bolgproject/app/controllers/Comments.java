package controllers;

import models.Blog;
import models.Comment;
import play.api.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.*;

public class Comments extends Controller{
	public static Result index(){
		return ok(
				dashboard.render(
						Blog.find.all(),
						Comment.find.all(),
						request().username()));
	}
	
	public static Result add(Long blog){
		Form<Comment> comForm=form(Comment.class).bindFromRequest();
		Comment.create(comForm, blog, request().username(), form().bindFromRequest().get("text"));
	}

}
