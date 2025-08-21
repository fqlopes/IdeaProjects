package dev.lpa;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//        Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java MasterClass");
        Course math = new Course("MATH", "Advanced Mathematics");
        Course goMC = new Course("GOMC", "GO Master Class");
        Course ptBR = new Course("PTBR", "Brazilian Portuguese Class");
        Course stream = new Course("STREAM", "Java Streams Class");


        // pool of 1000 students
        Student[] myStudents = new Student[1000];
        Arrays.setAll(myStudents, i -> Student.getRandomStudent(pymc, jmc, math, goMC, ptBR, stream));

        // How many male and female students are in the group?

        var males = Arrays.stream(myStudents)
                .filter(i -> i.getGender().equals("M"));

        var females = Arrays.stream(myStudents)
                .filter(i -> i.getGender().equals("F"));

        var genderUndefined = Arrays.stream(myStudents)
                        .filter( i -> i.getGender().equals("U"));

        System.out.println("Question: How many male and female students are in the group?");
        System.out.println("We have " + males.count() + " males and " + females.count() + " in total.");
        System.out.println(genderUndefined.count() + " have not answered their gender.");

        // Course's solution

        for (String gender : List.of("M", "F", "U")){
            var student = Arrays.stream(myStudents)
                    .filter(i -> i.getGender().equals(gender));
            System.out.println("# of " + gender + " is = " + student.count());
        }

        // How many students fall into the three age ranges

        var lessThan30 = Arrays.stream(myStudents)
                .filter(i -> i.getAge() < 30);
        var between30And60 = Arrays.stream(myStudents)
                .filter(i -> i.getAge() >= 30 && i.getAge() <= 60);
        var over60 = Arrays.stream(myStudents)
                .filter(i -> i.getAge() > 60);

        System.out.println("-".repeat(90));
        System.out.println("We divide them into three age categories:\n");
        System.out.println(lessThan30.count() + " of them are less than 30.");
        System.out.println(between30And60.count() + " of them are between 30 and 60.");
        System.out.println(over60.count() + " of them are over 60.\n");

        // Course's Solution

        List<Predicate<Student>> list = List.of(
                s -> s.getAge() < 30,
                s -> s.getAge() >= 30 && s.getAge() <= 60
        );

        long total = 0;
        for (int i = 0; i < list.size(); i ++){
            var myFilter = Arrays.stream(myStudents)
                    .filter(list.get(i));
            long cnt = myFilter.count();
            total += cnt;
            System.out.printf("# of students (%s) = %d%n", i == 0 ? " < 30 " : " >= 30 && <= 60 ", cnt);
        }
        System.out.println("# of students >= 60 = " + (myStudents.length - total));

        // Data analysis of the enrolled age and current age

        var ageStream = Arrays.stream(myStudents)
                .mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for enrollment age = " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(myStudents)
                .mapToInt(Student::getAge);
        System.out.println("Stats for current age = " + currentAgeStream.summaryStatistics());


        // What countries are the students from? Print a distinct list of the country codes.

        System.out.println("We have students from many places in the world");

        Arrays.stream(myStudents)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        // Print the students that have been enrolled for more than 7 years.

        long longTermCount = Arrays.stream(myStudents)
                .filter (s -> (s.getAge() - s.getAgeEnrolled() >= 7) && s.getMonthsSinceActive() < 12)
                        .count();
        System.out.println("\nWe currently have " + longTermCount + " long time students ");


        //Making a limited list of 5 of them

        System.out.println("Here are five of them: ");
        Arrays.stream(myStudents)
                .filter (s -> (s.getAge() - s.getAgeEnrolled() >= 7) && s.getMonthsSinceActive() < 12)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5);
                //.forEach(System.out::println);

        // The challenge ends here. The code afterwards refers to class 260

        var longTimeLearners =Arrays.stream(myStudents)
                .filter (s -> (s.getAge() - s.getAgeEnrolled() >= 7) && s.getMonthsSinceActive() < 12)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList();
        // This stream returns an unmodifiable list. You can then pass it to an arraylist
        // constructor to make our own modifiable list

        var longTimers = Arrays.stream(myStudents)
                .filter (s -> (s.getAge() - s.getAgeEnrolled() >= 7) && s.getMonthsSinceActive() < 12)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
//                .toArray(i -> new Student[i]);
                .toArray(Student[]::new);

        var learners = Arrays.stream(myStudents)
                .filter (s -> (s.getAge() - s.getAgeEnrolled() >= 7) && s.getMonthsSinceActive() < 12)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);
    }
}
