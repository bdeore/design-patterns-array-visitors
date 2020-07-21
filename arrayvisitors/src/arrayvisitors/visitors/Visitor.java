package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;

public interface Visitor {

  void visit(MyArrayI myArray);

  void visit(MyArrayListI myArrayList);
}
