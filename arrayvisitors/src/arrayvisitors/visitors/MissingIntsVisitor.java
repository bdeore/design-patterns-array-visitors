package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.Results;

public class MissingIntsVisitor implements Visitor {

  private final Results rs;

  public MissingIntsVisitor(Results rs) {
    this.rs = rs;
  }

  @Override
  public void visit(MyArrayI myArray) {
    int[] commonInts = new int[100];

    for (int i = 0; i < myArray.size(); i++) {
      commonInts[myArray.get(i)] = 1;
    }

    for (int i = 0; i < 100; i++) {
      if (commonInts[i] != 1) {
        System.out.println("i: " + i + " " + commonInts[i]);
        rs.store(i);
      }
    }
  }

  @Override
  public void visit(MyArrayListI myArrayList) {}
}
