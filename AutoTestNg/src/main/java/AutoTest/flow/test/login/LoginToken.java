package AutoTest.flow.test.login;



import org.junit.Test;

public class LoginToken extends LoginAction{


	public void inject() {
		this.useraccount="jinx0321";
		this.password="jinx@0321";
		this.type="comp";
		
	}

	
	
	public String login(String sa) {
		return utoken;
	}

	@Test
	public void test1() {
		System.out.println(utoken);
	}
	
	

}
