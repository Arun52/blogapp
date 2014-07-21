import models.Blog;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

	public void onStart(Application app) {

		Logger.info("init data");
		if (User.findbyEmail("bob@gmail.com") == null) {
			User u1 = new User("Bob", "dude", "bob@gmail.com");
			u1.save();
		}
//		if(Blog.findUnique("okay", "hey,whats up??")==null){
//			Blog b1=new Blog();
//			b1.header="okay";
//			b1.text="hey,whats up ??";
//			b1.save();
//		}
		
	}
}
