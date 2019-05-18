import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class TestLombok {
	@Test
	public void runTest() {
		Demo d= new Demo();
		System.out.println(d);
		d.setId(1);d.setName("Abc");d.setMarks(new BigDecimal("5.5"));
		System.out.println(d);
		//d= new Demo(2, "Bcd", new BigDecimal("10.1"), new HashSet<>());
		//System.out.println(d);
		d= new Demo("Cde", new BigDecimal("15.2"), new HashSet<>(), new Date(-1l), new Temp());
		System.out.println(d);
		System.out.println(d.equals(d));
		System.out.println(d.equals(new Demo()));
		System.out.println(d.hashCode());
		System.out.println(d.getDate().equals(new Date(-1l)));
		d=new Demo();
		d.setName("Def");
		System.out.println(d);
	}
}
