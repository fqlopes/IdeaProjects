public class ComplexNumber {
    //instance variables -> means they're not static,
    // and will be assigned for each new instance of this class
    private double real;
    private double imaginary ;
    //the one constructor. to construct them all
    public ComplexNumber (double real, double imaginary){

    //this needs to be declared, or the code won't work.
        this.real = real;
        this.imaginary = imaginary;
    }
    public double getReal() {
        return real;
    }
    public double getImaginary() {
        return imaginary;
    }
    public void add (double real, double imaginary){
        this.real += real;
        this.imaginary += imaginary;
    }
    public void add (ComplexNumber complexNumber){
        this.real = this.real + complexNumber.real;
        this.imaginary = this.imaginary + complexNumber.imaginary;
    }
    public void subtract (double real, double imaginary){
        this.real = this.real - real;
        this.imaginary = this.imaginary - imaginary;
    }
    public void subtract (ComplexNumber complexNumber){
        this.real = this.real - complexNumber.real;
        this.imaginary = this.imaginary - complexNumber.imaginary;
    }
}
