import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

import org.junit.jupiter.api.Test;

import com.saptris.erp.pmm.ReminderScheduler;

public class TestReminderScheduler {
	//@Test
	public void runTest1() {
		Timer timer= new Timer();
		//timer.schedule(new MaintenanceReminderTask(), Date.from(LocalDateTime.now()/*of(2019, 3, 19, 12, 32, 00)*/.atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	//@Test
	public void runTest2() {
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
	
	String id= "Asia/Kolkata";
	@Test
	public void runTest() {
		System.out.println(ReminderScheduler.getZoneDate(new Date()));
		
		System.out.println(Date.from(new Date().toInstant().atZone(ZoneId.systemDefault()/*of(id)*/).toLocalDateTime().atZone(ZoneId.of(id)).toInstant()));
		
		System.out.println(getZoneLocalDateTime(new Date()));
		System.out.println(getZoneDate(new Date()));
	}
	
	public LocalDateTime getZoneLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.of(id)/*systemDefault()*/).toLocalDateTime();
		//time is in 24hr format
		//LocalDateTime reminderDateTime= LocalDateTime.of(2019, 3, 19, 14, 50, 00);
	}
	
	public Date getZoneDate(Date date) {
		LocalDateTime dateTime= getZoneLocalDateTime(date);
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
