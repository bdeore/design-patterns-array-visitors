package arrayvisitors.adt;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;

public interface MyArrayListI extends Element {

  void add(MyArrayI element);

  MyArray get(int index);

  int size();

  int capacity();

  void ensureCapacity(int minCapacity);

  void clear();

  void accept(Visitor v) throws InvalidADTException;
}
