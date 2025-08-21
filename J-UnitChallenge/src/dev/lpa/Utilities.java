package dev.lpa;

public class Utilities {


    //Returns a char array containing every nth char,
    //when sourceArray.length < n, returns sourceArray
    //Example: if our array is 'a', 'b', 'c', 'd', 'e', 'f', 'g' and N is 2
    //Results b,d,f
    public char[] everyNthChar(char[] sourceArray, int n){

        if(sourceArray == null || sourceArray.length < n){
            return sourceArray;
        }

        int returnedLength = sourceArray.length / n;
        char[] result = new char[returnedLength];
        int index = 0;

        for (int i = n-1; i < sourceArray.length; i += n){
            result[index++] = sourceArray[i];
        }
        return result;
    }

    //Remove pairs of the same character that are next of each other,
    //removing only one of them
    public String removePairs (String source){

        if (source.length() <2){
            return source;
        }

        StringBuilder sb = new StringBuilder();
        char[] string =  source.toCharArray();

        for (int i = 0; i < source.length(); i++){
            if(string[i] != string[i++]){
                sb.append(string[i]);
            }
        }
        return sb.toString();
    }

    public int converter(int a, int b){
        return (a/b) + (a* 30) - 2;
    }

    public String nullIfOddLength(String source){
        if (source.length() % 2 == 0){
            return source;
        }
        return null;
    }
}
