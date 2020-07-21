package arrayvisitors.adt;

import arrayvisitors.visitors.Visitor;

public class MyArrayList implements MyArrayListI {

  private int capacityIncrement;
  private MyArray[] myArrayList;
  private int currentSize;
  private int currentCapacity;

  public MyArrayList() {
    this(10);
  }

  public MyArrayList(int initialCapacity) {
    currentCapacity = initialCapacity;
    currentSize = 0;
    this.myArrayList = new MyArray[currentCapacity];
    this.capacityIncrement = -1;
  }

  public MyArrayList(int initialCapacity, int capacityIncrement) {
    this(initialCapacity);
    this.capacityIncrement = capacityIncrement;
  }

  @Override
  public void accept(Visitor v) {}

  @Override
  public void add(MyArrayI element) {
    if (currentSize < currentCapacity) {
      myArrayList[currentSize] = (MyArray) element;
      currentSize++;
    } else {
      int newCapacity =
          capacityIncrement == -1
              ? currentCapacity + (int) (0.5 * currentCapacity)
              : currentCapacity + capacityIncrement;

      ensureCapacity(newCapacity);
      System.out.println("Size Exceeded");
      add(element);
    }
  }

  @Override
  public MyArray get(int index) {
    if (index <= currentSize && this.myArrayList != null) return this.myArrayList[index];
    return null;
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  public int capacity() {
    return currentCapacity;
  }

  @Override
  public void ensureCapacity(int minCapacity) {

    MyArray[] temp = new MyArray[minCapacity];

    for (int i = 0; i < currentSize; i++) {
      temp[i] = myArrayList[i];
    }

    this.myArrayList = temp;
    this.currentCapacity = minCapacity;
  }

  @Override
  public void clear() {
    this.myArrayList = null;
    this.myArrayList = new MyArray[currentCapacity];
    currentSize = 0;
  }
}
