package DONE.Section6;

public class LargestPrime {

    public static int getLargestPrime(int number) {

        if ( number < 2 ){
            return -1;
        } else {

            int factor = 1;

            for ( int i = 2; i*i <= number; i++){

                if( number % i != 0){
                    continue;
                }
                factor = i;
                while (number%i == 0){
                    number /=i;
                }
            }
            return number == 1 ? factor : number;
        }
    }
}
