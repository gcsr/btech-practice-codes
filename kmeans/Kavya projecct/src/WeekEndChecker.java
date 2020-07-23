
public class WeekEndChecker {
	public static void main(String[] gcs){
		System.out.println("called "+HolidayChecker.isHoliday(new Day(2015,2,7)));
		System.out.println("called "+HolidayChecker.isWeekEnd(new Day(2015,2,7)));
	}
}
