/*
	Counting Days from the start of the year
	
	Given today's date, tell us which days of the year today is.
	For example, Jan 1 is the first day of the year
	December 31 is the 365th day of the year (if the year is not a leap year), etc.
*/
import java.awt.*;
       import java.applet.Applet;
public class CountingDays extends Applet
       {
   int day, month, year;
   int dayNumber;
   
   public void init() {
   
      // initialize the variables to today's date
      day = 13;
      month = 4;
      year = 2010;
      // Later we will see how to get this either form the user
      // or from within the computer itself
   
      // count the days
      dayNumber = countDays(day, month, year);
   
   } // init
   
   public void paint(Graphics g) {
      // print the value of date and day number
      g.drawString("Today is"+month+"/"+day+"/"+year,   20, 100);
      g.drawString(""+dayNumber+" days have passed.",   20, 120);
   } // paint
   
   private int countDays(int d, int m, int y) {
   
      int result = 0;
   
      // Start from Jan, and go all the way to m-1 and count
      // all the days in full months from Jan to m-1
      for (int mo = 4; mo < m; mo++)
      result = result + daysInMonth(mo, y);
   
      // Add days in current month
      result = result + d;
   
      return result;
   } // countDays
   
   private int daysInMonth(int m, int y) {
      // return the number of days in the month m, in year y
      switch (m) {
         case 1:
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         case 12: return 31;
         case 4:
         case 6:
         case 9:
         case 11: return 30;
         case 2: if (leapYear(y))
                    return 29;
                 else
                    return 28;
      } // switch
      return 0;	// This is needed. If it gets here, m was not a valid #
   } // daysInMonth
   
   private boolean leapYear(int y) {
      // returns true is y is a leap year
      //return ((y % 4 == 0 && y%100 != 0) || (y%400 == 0));
      if ((y % 4 == 0 && y%100 != 0) || (y%400 == 0))
         return true;
      else
      return false;
   } // leapYear
}// CountingDays