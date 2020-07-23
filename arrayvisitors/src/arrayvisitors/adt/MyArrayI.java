package arrayvisitors.adt;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;

/** Interface to define API for MyArray class */
public interface MyArrayI extends Element {

  void add(int element);

  void remove(int element);

  int get(int index);

  int size();

  int capacity();

  void ensureCapacity(int minCapacity);

  void clear();

  int indexOf(int element);

  boolean contains(int element);

  void print();

  void accept(Visitor v) throws InvalidADTException;
}
