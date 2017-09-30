package testHarness;

/**
 *
 * @author 
 */
public class TestHarness {

    public static void main(String[] args) {
        getLogin();
    }
    public static void getLogin(){
        String userName = "testuser";
        String password = "password";
        login.LoginController.login(userName, password);
    }
}
