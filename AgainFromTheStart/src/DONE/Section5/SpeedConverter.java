package DONE.Section5;
//NOTE: 1 mile per hour is 1.609 kilometers per hour
public class SpeedConverter {

   public static long toMilesPerHour (double kilometersPerHour){
       if (kilometersPerHour < 0) return -1;
       return Math.round(kilometersPerHour/1.609);
   }

    public static void printConversion (double kilometersPerHour){
        if (kilometersPerHour < 0){
            System.out.println("Invalid Value");
        }else {

            int milesPerHour = Math.round(toMilesPerHour(kilometersPerHour));
            System.out.println(kilometersPerHour +" km/h = "+milesPerHour+ " mi/h");
        }
    }
}
