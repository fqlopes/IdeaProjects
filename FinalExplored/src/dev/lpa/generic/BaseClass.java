package dev.lpa.generic;

public class BaseClass {

    //making this method final will prohibit its subclasses to implement it
    public final void recommendedMethod (){

        System.out.println("[BaseClass.recommendedMethod]: Best way to do it");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod (){
        System.out.println("[BaseClass.optionalMethod]: Customize Optional Method");
    }

//    this method being private means that polymorphism is prohibited
    private void mandatoryMethod (){
        System.out.println("[BaseClass.mandatoryMethod]: NON-NEGOTIABLE!");
    }

    public static void recommendedStatic(){
        System.out.println("[BaseClass.recommendedStatic]: BEST way to do it!");
        optionalStatic();
        mandatoryStatic();
    }

    protected static void optionalStatic (){
        System.out.println("[BaseClass.optionalStatic]: Optional");
    }

    private static void mandatoryStatic (){
        System.out.println("[BaseClass.mandatoryStatic]: MANDATORY");
    }
}
