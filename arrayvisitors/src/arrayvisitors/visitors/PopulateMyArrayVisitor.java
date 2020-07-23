package arrayvisitors.visitors;

import arrayvisitors._exceptions.EmptyInputFileException;
import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;
import java.io.IOException;

/** visitor to populate array using integers in input files */
public class PopulateMyArrayVisitor implements Visitor {

  private final String fileName;
  private final FileProcessor fp;

  /**
   * parameterized constructor
   *
   * @param fileName name of the input file
   * @throws IOException exception
   */
  public PopulateMyArrayVisitor(String fileName) throws IOException {
    this.fileName = fileName;
    this.fp = new FileProcessor(fileName);
  }

  /**
   * visit method to populate myArray objects. does bunch of error and exception handling
   *
   * @param myArray MyArray object
   */
  @Override
  public void visit(MyArrayI myArray) {
    int count = 0;
    try {
      String line = fp.poll();
      while (line != null) {
        int digit = Integer.parseInt(line);
        myArray.add(digit);
        count++;
        line = fp.poll();
      }
      if (count == 0) throw new EmptyInputFileException();
    } catch (NumberFormatException e) {
      System.out.println(
          "Invalid word found in input file : [ "
              + this.fileName
              + " ] : Line Number => "
              + (count + 1));
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    } catch (IOException | NullPointerException | EmptyInputFileException e) {
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    }

    MyLogger.getInstance().store("capacity : " + myArray.capacity());
    MyLogger.getInstance().store("size : " + myArray.size());
    // myArray.print();
  }

  /**
   * invalid method
   *
   * @param myArrayList MyArrayList object
   * @throws InvalidADTException exception is thrown if visitor is applied on unsupported ADT
   */
  @Override
  public void visit(MyArrayListI myArrayList) throws InvalidADTException {
    throw new InvalidADTException();
  }

  /**
   * toString method for debugging
   *
   * @return String
   */
  @Override
  public String toString() {
    return "PopulateMyArrayVisitor : " + "fileName='" + fileName + '\'' + ", fp=" + fp;
  }
}
