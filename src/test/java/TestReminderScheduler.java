import java.util.Timer;

import org.junit.jupiter.api.Test;

import com.saptris.erp.pmm.ReminderScheduler;

public class TestReminderScheduler {
	//@Test
	public void runTest1() {
		Timer timer= new Timer();
		//timer.schedule(new MaintenanceReminderTask(), Date.from(LocalDateTime.now()/*of(2019, 3, 19, 12, 32, 00)*/.atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	@Test
	public void runTest() {
		ReminderScheduler rs= new ReminderScheduler();
		rs.start();
		try {
			rs.join();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ReminderScheduler.stopScheduler();
	}
}
