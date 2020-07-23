package arrayvisitors.visitors;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.Results;

/** visitor to find all elements missing from the array between 00 and 99 (inclusive) */
public class MissingIntsVisitor implements Visitor {

  private final Results rs;

  /**
   * parameterized constructor
   *
   * @param rs results object to be used
   */
  public MissingIntsVisitor(Results rs) {
    this.rs = rs;
  }

  /**
   * visit method to find missing elements from the array between 00 and 99
   *
   * <p>for explanation about the algorithm and time complexity see README.md
   *
   * @param myArray
   */
  @Override
  public void visit(MyArrayI myArray) {
    int[] commonInts = new int[100];

    for (int i = 0; i < myArray.size(); i++) {
      commonInts[myArray.get(i)] = 1;
    }

    for (int i = 0; i < 100; i++) {
      if (commonInts[i] != 1) {
        String num = (i < 10 ? "0" : "") + i;
        rs.store(num);
      }
    }
  }

  /**
   * invalid method
   *
   * @param myArrayList
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
    return "MissingIntsVisitor : " + "rs=" + rs;
  }
}
