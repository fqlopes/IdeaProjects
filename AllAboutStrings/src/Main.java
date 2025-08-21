public class Main {
    public static void main(String[] args) {

        printInformation("Hello World");
        printInformation("");
        printInformation("\t   \n");

        String helloWorld = "Hello World";
        printInformation(helloWorld);
        System.out.printf("index of r = %d %n", helloWorld.indexOf('r'));
        System.out.printf("index of World = %d %n", helloWorld.indexOf("World"));

        System.out.printf("index of l = %d %n", helloWorld.indexOf('l'));
        System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l'));

        System.out.printf("index of l = %d %n", helloWorld.indexOf('l',3));
        System.out.printf("index of l = %d %n", helloWorld.lastIndexOf('l',8));

        String helloWorldLower = helloWorld.toLowerCase();

        if (helloWorld.equals(helloWorldLower)) {
            System.out.println("Value matches exactly");
        }

        if (helloWorld.equalsIgnoreCase(helloWorldLower)) {
            System.out.println("Value matches exactly ignoring case");
        }

        if (helloWorld.startsWith("Hello")){
            System.out.println("String starts with Hello");
        }

        if (helloWorld.endsWith("World")){
            System.out.println("String ends with World");
        }
        if (helloWorld.contains("World")){
            System.out.println("String contains World");
        }
        if (helloWorld.contentEquals("Hello World")){
            System.out.println("Values do matches exactly");
        }

    }

    public static void printInformation (String string){

        int length = string.length();
        System.out.printf("Length = %d %n", length);

        if (string.isEmpty()) {
            System.out.printf("The string is empty. %n");
            return;
        }

        if (string.isBlank()) {
            System.out.printf("The string is blank. %n");
        }
        System.out.printf("First char = %c %n", string.charAt(0));

        System.out.printf("Last char = %c %n", string.charAt(length -1));

    }
}
