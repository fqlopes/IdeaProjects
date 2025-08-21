import javax.swing.plaf.metal.MetalLabelUI;

public class Main {
    public static void main(String[] args) {

//        Burger burger = new Burger("Regular", 4.00);
//        burger.addToppings("BACON","CHEESE","MAYO");
//        burger.printItem();


//        MealOrder regularMeal = new MealOrder();
//        regularMeal.addBurgerToppings("BACON","CHEESE","MAYO");
//        regularMeal.setDrinkSize("LARGE");
//        regularMeal.printItemizedList();

//        MealOrder secondOrder = new MealOrder("turkey", "7-up","chili"  );
//        secondOrder.addBurgerToppings("LETTUCE","CHEESE","MAYO");
//        secondOrder.setDrinkSize("SMALL");
//        secondOrder.printItemizedList();

        MealOrder deluxeMeal = new MealOrder("deluxe", "7-up","chili");
        deluxeMeal.addBurgerToppings("Avocado","Lettuce","Bacon","Cheese","Mayo");
        deluxeMeal.setDrinkSize("SMALL");
        deluxeMeal.printItemizedList();


    }

}
