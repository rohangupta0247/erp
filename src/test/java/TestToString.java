import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.saptris.erp.db.ItemTemp;
import com.saptris.erp.db.Warehouse;

public class TestToString {
	@Test
	public void runTestItemTemp() {
		ItemTemp i= new ItemTemp("this, is \"abc\"", new Warehouse(), 1, 1.2, new Date(1, 1, 1),23);
		System.out.println(i);
	}
}
