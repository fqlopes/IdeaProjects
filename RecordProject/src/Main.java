public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++){
            LPAStudent s = new LPAStudent ("ID " + i,
                    switch (i){ //bruh
                        case 1 -> "Louis";
                        case 2 -> "Adam";
                        case 3 -> "Lisa";
                        case 4 -> "Monica";
                        case 5-> "Patricia";
                        default -> "Unknown";
                    }, "09/08/92","All");
            System.out.println(s);
        }
        Student pojoStudent = new Student("0", "POJOBOY","11/11/93", "All at once");
        LPAStudent lpaStudent = new LPAStudent("1", "LPAGIRL", "12/12/90", "All at once");

        System.out.println(pojoStudent);
        System.out.println(lpaStudent);

        pojoStudent.setClassList(pojoStudent.getClassList() + " and more");
// imutable        lpaStudent.classList()
        System.out.println(pojoStudent.getName() + " is taking " + pojoStudent.getClassList());
        System.out.println(lpaStudent.name() + " is taking " + lpaStudent.classList());


    }
}
