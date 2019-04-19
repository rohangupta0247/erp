import org.junit.jupiter.api.Test;

import com.saptris.erp.SessionFactoryBuilder;

public class TestSessionFactoryBuilder {
	@Test
	public void runTestGetAllClasses() {
		for(Class<?> c: SessionFactoryBuilder.getAllClasses("target\\classes" , "com.saptris.erp.db")) {
			System.out.println(c);
		}
	}
}
