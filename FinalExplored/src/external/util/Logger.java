package external.util;

import java.time.LocalDateTime;

public class Logger {

//    char sequence -> takes either a String or a StringBuilder
    public static void logToConsole(CharSequence message){

        LocalDateTime dt = LocalDateTime.now();

//         'D' 	Date formatted as "%tm/%td/%ty" -> MONTH/DAY/YEAR
//         'T' 	Time formatted for the 24-hour clock as "%tH:%tM:%tS" -> HOUR:MINUTE:SECOND
        System.out.printf("%1$tD %1$tT : %2$s%n", dt, message);
        if (message instanceof StringBuilder sb) {
            sb.setLength(0);
        }

    }
}
