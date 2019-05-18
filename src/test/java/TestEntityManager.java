import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.saptris.erp.EntityManager;

public class TestEntityManager {
	//not works now as validation now needs session
	//@BeforeAll
	//public static void login() {
		//String username= "first", password= "pass";
		//assertEquals(UserManager.VALID_USER, UserManager.validateLogin(username, password, new User()));
	//}
	
	//@Test
	public void runTestGetAllEntityString() {
		EntityManager em= new EntityManager("ItemTemp");
		for(String str[]: em.getAllEntityString(4, 1)) {
			for(String s: str) {
				System.out.print(s+"<<");
			}
			System.out.println();
		}
	}
	
	//@Test
	public void runTestSearch() {
		EntityManager em= new EntityManager("ItemTemp");
		String res[][]= em.search("Item Name", "tem");
		assertNotNull(res);
		for(String str[]: res) {
			for(String s: str) {
				System.out.print(s+"<->");
			}
			System.out.println();
		}
	}
	
	//@Test
	public void runTestToNamingCaseFromCamelCase() {
		System.out.println(EntityManager.toNamingCaseFromCamelCase("ItemTemp"));
	}
	
	//@Test
	public void runTestToUnderscoreCaseFromCamelCase() {
		System.out.println(EntityManager.toUnderscoreCaseFromCamelCase("ItemTemp"));
	}
	
	@Test
	public void runTestToCamelCase() {
		System.out.println(EntityManager.toCamelCase("item_temp"));
	}
}
