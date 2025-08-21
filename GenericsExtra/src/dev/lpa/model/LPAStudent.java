package dev.lpa.model;

public class LPAStudent extends Student{

    private double percentComplete;

    public LPAStudent (){
        percentComplete = random.nextDouble(10, 100.001);
    }


    @Override
    public String toString (){
        return "%s %.2f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }
}
