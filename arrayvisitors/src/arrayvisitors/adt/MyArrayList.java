package arrayvisitors.adt;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.visitors.Visitor;
import java.util.Arrays;

public class MyArrayList implements MyArrayListI, Cloneable {

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
  public void accept(Visitor v) throws InvalidADTException {
    v.visit(this);
  }

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

  public MyArray getClone(MyArrayI array) throws CloneNotSupportedException {
    return (MyArray) ((MyArray) array).clone();
  }

  public void setMyArray(MyArrayI array, int index) {
    this.myArrayList[index] = (MyArray) array;
  }

  public MyArray[] getMyArrayList() {
    return myArrayList;
  }

  public void setCapacityIncrement(int capacityIncrement) {
    this.capacityIncrement = capacityIncrement;
  }

  @Override
  protected void finalize() throws Throwable {}

  @Override
  public Object clone() throws CloneNotSupportedException {
    MyArrayList copy = (MyArrayList) super.clone();
    copy.myArrayList = new MyArray[this.currentCapacity];

    for (int i = 0; i < this.currentSize; i++) {
      copy.add((MyArrayI) ((this.myArrayList[i]).clone()));
    }
    return copy;
  }

  @Override
  public String toString() {
    return "MyArrayList : "
        + "capacityIncrement="
        + capacityIncrement
        + ", myArrayList="
        + Arrays.toString(myArrayList)
        + ", currentSize="
        + currentSize
        + ", currentCapacity="
        + currentCapacity;
  }
}
