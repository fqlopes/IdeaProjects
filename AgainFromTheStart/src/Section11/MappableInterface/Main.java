package Section11.MappableInterface;



public class Main {

    public static void main(String[] args) {

        Mappable building = new Building("Home", usageType.HOME);

        System.out.println(building.toJSON());
    }
}
