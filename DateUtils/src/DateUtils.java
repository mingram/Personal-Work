import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtils {
  public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
  
  private double d;

  public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return sdf.format(cal.getTime());

  }

  public static void  main(String arg[]) {
    System.out.println("Now : " + DateUtils.now());
    System.out.println("After : "+DateUtils.nextDate());
  }
  
  public static String nextDate(){
	  Calendar cal = Calendar.getInstance();
	  SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	  cal.set(2010, 05, 11);
  
	  return sdf.format(cal.getTime());
  }

}