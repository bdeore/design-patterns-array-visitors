package arrayvisitors.visitors;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.Results;

/** visitor to find common integers among two arrays */
public class CommonIntsVisitor implements Visitor {

  private final Results rs;

  /**
   * parameterized constructor
   *
   * @param rs results object to be used
   */
  public CommonIntsVisitor(Results rs) {
    this.rs = rs;
  }

  /**
   * visit method to find common integers
   *
   * <p>for explanation about the algorithm and time complexity see README.md
   *
   * @param myArrayList MyArrayList object
   */
  @Override
  public void visit(MyArrayListI myArrayList) {
    int[] commonInts = new int[100];

    MyArrayI myArray1 = myArrayList.get(0);
    MyArrayI myArray2 = myArrayList.get(1);

    for (int i = 0; i < myArray1.size(); i++) {
      commonInts[myArray1.get(i)] = 1;
    }

    for (int i = 0; i < myArray2.size(); i++) {
      if (commonInts[myArray2.get(i)] == 1) commonInts[myArray2.get(i)] = 2;
    }

    for (int i = 0; i < 100; i++) {
      if (commonInts[i] == 2) {
        String num = (i < 10 ? "0" : "") + i;
        rs.store(num);
      }
    }
  }
  /**
   * invalid method
   *
   * @param myArray
   * @throws InvalidADTException exception is thrown if visitor is applied on unsupported ADT
   */
  @Override
  public void visit(MyArrayI myArray) throws InvalidADTException {
    throw new InvalidADTException();
  }

  /**
   * toString method for debugging
   *
   * @return String
   */
  @Override
  public String toString() {
    return "CommonIntsVisitor : " + "rs=" + rs;
  }
}
