public class StringMethods {
    public static void main(String[] args) {

        String birthDate = "08/09/1992";
        int StartingIndex = birthDate.indexOf("1992");
        System.out.println("Starting Index = " + StartingIndex);
        System.out.println("Birth date = " + birthDate.substring(StartingIndex));

        System.out.println("Birth Month = " + birthDate.substring(3,5));

        String newDate = String.join("/","05","02","2025");
        System.out.println(newDate);

        System.out.println(newDate.replace('/','-'));
        System.out.println(newDate.replace("2","00"));

        System.out.println(newDate.replaceFirst("/","-"));
        System.out.println(newDate.replaceAll("/","---"));

        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(20));

        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(20));

        System.out.println("    ABC\n".repeat(3).indent(-2));
        System.out.println("-".repeat(20));
    }

}
