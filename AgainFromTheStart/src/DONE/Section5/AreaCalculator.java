package DONE.Section5;

public class AreaCalculator {

    public static double area (double radius){
        return radius < 0 ? -1 : (radius*radius *Math.PI);
    }

    public static double area (double x, double y){


        if (x >= 0 && y >= 0){
            return x*y;
        }
        return -1;
    }
}
