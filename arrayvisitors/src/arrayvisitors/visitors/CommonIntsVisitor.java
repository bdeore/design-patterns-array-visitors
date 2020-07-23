package arrayvisitors.visitors;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.Results;

public class CommonIntsVisitor implements Visitor {

  private final Results rs;

  public CommonIntsVisitor(Results rs) {
    this.rs = rs;
  }

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

  @Override
  public void visit(MyArrayI myArray) throws InvalidADTException {
    throw new InvalidADTException();
  }
}
