package dev.lpa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainChallenge {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course jgames = new Course("JGAMES", "Creating Games in Java");

//    - Use stream.generate or iterate to generate 5k students and create
//      a list of these

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc)).toList();

//        - Use getPercentComplete method, to calculate the average percent completed
//        for all students just for jmc, using the reduce terminal operation

        double totalPercent = students
                .stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);

        double avePercent = totalPercent / students.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avePercent);

        int topPercent = (int) (1.25 * avePercent);
        System.out.printf("Best percentage Complete = %d%% %n", topPercent);

//        se this result, multiplying by 1.25, to collect a group of students(either as a list,
//        or a set). These would be the students who've completed more than three quarters of that
//        average percentage.

        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);
        List<Student> hardWorkers = students
                .stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent).limit(10).toList();

        hardWorkers.forEach(student -> {
            student.addCourse(jgames);
            System.out.println(student);
        });


    }
}
