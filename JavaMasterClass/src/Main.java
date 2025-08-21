public class Main {

    public static void main(String[] args) {

        Car car = new Car();
        car.setMake("Gaymobile");
        car.setColor("Midnight Black");
        car.setConvertible(false);
        car.setModel("...,why would you do that?");
        car.setDoors(4);


        System.out.println("make = " + car.getMake());
        System.out.println("model = "+ car.getModel());
        car.describeCar();


        Car brasilia = new Car();
        System.out.println("Carro brasileiro é tipo: ");
        brasilia.setMake("Raiz");
        brasilia.setColor("Azul bebe");
        brasilia.setConvertible(true);
        brasilia.setModel(" Veiculo oficial da violencia domestica");
        brasilia.setDoors(2);

        brasilia.describeCar();
/*
        System.out.println(car.getColor());
        System.out.println(car.getDoors());
        System.out.println(car.getMake());
        System.out.println(car.getClass());
        System.out.println(car.getModel());
 */
    }
}
