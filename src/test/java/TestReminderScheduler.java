import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;
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
	String sysid= "UTC";
	@Test
	public void runTest() {
		//System.out.println(ReminderScheduler.getZoneDate(new Date()));
		
		System.out.println(Date.from(new Date().toInstant().atZone(ZoneId.systemDefault()/*of(id)*/).toLocalDateTime().atZone(ZoneId.of(id)).toInstant()));
		
		System.out.println(getZoneLocalDateTime(new Date()));
		//System.out.println(getDate(new Date()));
		
		
		LocalDateTime reminderDateTime=null;
		try {
			//reminderDateTime = ReminderScheduler.getZoneLocalDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2019-05-23T22:30"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDateTime currentDateTime= null;
		//currentDateTime= ReminderScheduler.getZoneLocalDateTime(new Date());
		//if(reminderDateTime.isBefore(currentDateTime)) {
			//System.out.println("ok");
		//}
		
		
		reminderDateTime=null;
		Date date= null;
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			date= sdf.parse("2019-05-23T22:30");
			String temp= sdf.format(date);
			sdf.setTimeZone(TimeZone.getTimeZone(id));
			date= sdf.parse(temp);
			reminderDateTime = getLocalDateTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentDateTime= getZoneLocalDateTime(new Date());
		if(reminderDateTime.isBefore(currentDateTime)) {
			System.out.println("ok");
		}
		//System.out.println(getDate(date));
		System.out.println(getRequiredZoneDate(new Date()));
	}
	
	public LocalDateTime getZoneLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.of(id)/*systemDefault()*/).toLocalDateTime();
		//time is in 24hr format
		//LocalDateTime reminderDateTime= LocalDateTime.of(2019, 3, 19, 14, 50, 00);
	}
	
	public LocalDateTime getLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.of(id)/*systemDefault()*/).toLocalDateTime();
		//time is in 24hr format
		//LocalDateTime reminderDateTime= LocalDateTime.of(2019, 3, 19, 14, 50, 00);
	}
	
	public Date getRequiredZoneDate(Date date) {
		LocalDateTime dateTime= date.toInstant().atZone(ZoneId.of(id)).toLocalDateTime();
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
