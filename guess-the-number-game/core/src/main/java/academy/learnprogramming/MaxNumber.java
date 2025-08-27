package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target indicates the context in which an annotation type is applicable
//In this case, it can be applicable for fields, parameters and methods
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})

//@Retention indicates how long annotations are to be retained
@Retention(RetentionPolicy.RUNTIME)
//@Qualifier is a Spring annotation, used to annotate other custom annotations
//that in turn can be used as qualifiers
@Qualifier
public @interface MaxNumber {

}
