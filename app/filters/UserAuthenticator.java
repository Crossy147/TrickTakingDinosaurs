package filters;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.Application;

public class UserAuthenticator extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get(Application.USER);
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(Application.HOME);
	}
	
}