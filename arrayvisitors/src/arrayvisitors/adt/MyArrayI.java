package arrayvisitors.adt;

public interface MyArrayI {

  void add(int element);

  void remove(int element);

  int get(int index);

  int size();

  int capacity();

  void ensureCapacity(int minCapacity);

  void clear();

  int indexOf(int element);

  boolean contains(int element);


}
