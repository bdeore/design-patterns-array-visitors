package arrayvisitors._exceptions;

/**
 * user-defined exception class - this exception is thrown if visitor is applied on unsupported ADT
 */
public class InvalidADTException extends Exception {

  public InvalidADTException() {
    super("This visitor cannot be applied on specified ADT");
  }

  public InvalidADTException(String message) {
    System.out.println(
        "InvalidADTException: " + message + " This visitor cannot be applied on specified ADT");
  }
}
