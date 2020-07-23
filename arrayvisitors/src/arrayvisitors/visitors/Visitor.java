package arrayvisitors.visitors;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;

public interface Visitor {

  void visit(MyArrayI myArray) throws InvalidADTException;

  void visit(MyArrayListI myArrayList) throws InvalidADTException;
}
