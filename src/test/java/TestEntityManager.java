import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.saptris.erp.EntityManager;
import com.saptris.erp.User;
import com.saptris.erp.UserManager;

public class TestEntityManager {
	@BeforeAll
	public static void login() {
		String username= "first", password= "pass";
		assertEquals(UserManager.VALID_USER, UserManager.validateLogin(username, password, new User()));
	}
	
	@Test
	public void runTestGetAllEntityString() {
		EntityManager em= new EntityManager("ItemTemp");
		for(String str[]: em.getAllEntityString(4, 1)) {
			for(String s: str) {
				System.out.print(s+"<<");
			}
			System.out.println();
		}
	}
	
	@Test
	public void runTestSearch() {
		EntityManager em= new EntityManager("ItemTemp");
		String res[][]= em.search("Item Name", "tem");
		assertNotNull(res);
		assertEquals(1, res.length);
		for(String str[]: res) {
			for(String s: str) {
				System.out.print(s+"<->");
			}
			System.out.println();
		}
	}
}
