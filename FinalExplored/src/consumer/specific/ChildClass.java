package consumer.specific;

import dev.lpa.generic.BaseClass;

public class ChildClass extends BaseClass {

    @Override
    protected void optionalMethod() {
        System.out.println("[Child:optionalMethod] Extra stuff here!");
        super.optionalMethod();
    }
//    @Override
//    public void recommendedMethod() {
//        System.out.println("[Child.recommendedMethod]: I'll do things my way!");
//        optionalMethod();
//    }

//    the mandatory method does not override its counterpart in the BaseClass.
    private void mandatoryMethod(){
        System.out.println("[Child:mandatoryMethod]: My own important stuff here");
    }

//    this is called hiding a method.
    public static void recommendedStatic(){
        System.out.println("[ChildClass.recommendedStatic]: BEST way to do it!");
        optionalStatic();
//        mandatoryStatic();
    }
}
