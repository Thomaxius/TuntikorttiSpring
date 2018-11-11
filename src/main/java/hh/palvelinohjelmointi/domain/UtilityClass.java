package hh.palvelinohjelmointi.domain;

import java.text.DecimalFormat;
import java.util.Date;

public class UtilityClass {

	public static String getDifferenceInHours(Date date1, Date date2) {
		DecimalFormat f = new DecimalFormat("0.00");
		return f.format((((date2.getTime() - date1.getTime()) / 1000.0) / 60) / 60).replace(',', '.');
	}
	
	public static void getTotal(Object object) {
		System.out.println(object);
	}
	
	// Check if date is before another
	public static boolean dateIsAfterDate(Date date1, Date date2) {
		return date1.before(date2);
	}
	
}
