package arrayvisitors.visitors;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;

public interface Visitor {

  void visit(MyArray myArray);

  void visit(MyArrayList myArrayList);
}
