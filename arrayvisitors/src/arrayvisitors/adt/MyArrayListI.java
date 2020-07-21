package arrayvisitors.adt;

public interface MyArrayListI {

  void add(MyArrayI element);

  MyArray get(int index);

  int size();

  int capacity();

  void ensureCapacity(int minCapacity);

  void clear();
}
