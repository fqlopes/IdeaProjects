public class Main {
    public static void main(String[] args) {

        Student student = new Student("Felipe", 32);
        System.out.println(student);

        PrimarySchoolStudent jimmy = new PrimarySchoolStudent("Jimmy", 8, "Carole");
        System.out.println(jimmy);
        System.out.println();

    }
}

class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }


    @Override
    public String toString() {
        return name + " is " + age;
//        return "Student{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
    }
}

class PrimarySchoolStudent extends Student {

    private String parentName;

    PrimarySchoolStudent (String name, int age, String parentName){
        super (name, age); //super for assign values on the parent code.
        this.parentName = parentName;

    }

    @Override
    public String toString() {
        return parentName + "'s kid, "+ super.toString();
    }
}

