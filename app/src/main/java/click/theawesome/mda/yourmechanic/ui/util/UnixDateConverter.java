package click.theawesome.mda.yourmechanic.ui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UnixDateConverter {
   public static String convert(long timestamp) {
       Calendar mydate = Calendar.getInstance();
       mydate.setTimeInMillis(timestamp * 1000);
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
       return sdf.format(mydate.getTime());
   }
}