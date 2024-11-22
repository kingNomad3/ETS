public class ZeroCoefficientException extends IllegalArgumentException {
  public ZeroCoefficientException(){
    super("Terms with a zero coefficient are invalid");
  }
}