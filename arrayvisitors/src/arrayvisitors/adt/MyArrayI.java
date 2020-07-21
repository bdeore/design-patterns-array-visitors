package arrayvisitors.adt;

import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;

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

  void accept(Visitor v);
}
