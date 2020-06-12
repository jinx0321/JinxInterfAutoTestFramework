package AutoTest.flow.test.login;



public abstract class LoginAction {
	 protected String utoken;
 	 protected String useraccount;
	 protected String password;
	 protected String type;
	
	{
		try {
			inject();
			if(CookieStore.logininfo.containsKey(useraccount)) {
				utoken=CookieStore.logininfo.get(useraccount);
			}
			else {
				utoken=WJLJLogin.Check_Login(useraccount, password, type).get("utoken");
				CookieStore.logininfo.put(useraccount, utoken);
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	abstract protected void inject();
  
	
}
