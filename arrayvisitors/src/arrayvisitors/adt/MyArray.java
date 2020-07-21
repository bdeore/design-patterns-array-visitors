package arrayvisitors.adt;

import java.util.Arrays;

public class MyArray implements MyArrayI {

  private int capacityIncrement;
  private int[] myArray;
  private int currentSize;
  private int currentCapacity;

  public MyArray() {
    currentCapacity = 10;
    currentSize = 0;
    this.myArray = new int[currentCapacity];
    this.capacityIncrement = -1;
  }

  public MyArray(int initialCapacity) {
    currentCapacity = initialCapacity;
    currentSize = 0;
    this.myArray = new int[currentCapacity];
    this.capacityIncrement = -1;
  }

  public MyArray(int initialCapacity, int capacityIncrement) {
    currentCapacity = initialCapacity;
    currentSize = 0;
    this.capacityIncrement = capacityIncrement;
    this.myArray = new int[currentCapacity];
  }

  @Override
  public void add(int element) {
    if (currentSize < currentCapacity) {
      myArray[currentSize] = element;
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
  public void remove(int element) {
    int elementIndex = indexOf(element);

    if (elementIndex == -1) System.out.println("Element doesn't exist in the array");
    else {
      for (int i = elementIndex; i < currentSize; i++) myArray[i] = myArray[i + 1];
      currentSize--;
    }
  }

  @Override
  public int get(int index) {
    if (index <= currentSize && this.myArray != null) return this.myArray[index];
    return -1;
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
    int[] temp = new int[minCapacity];

    for (int i = 0; i < currentSize; i++) {
      temp[i] = myArray[i];
    }

    this.myArray = temp;
    this.currentCapacity = minCapacity;
  }

  @Override
  public void clear() {
    this.myArray = null;
    this.myArray = new int[currentCapacity];
    currentSize = 0;
  }

  @Override
  public int indexOf(int element) {
    for (int i = 0; i < currentSize; i++) {
      if (myArray[i] == element) return i;
    }
    return -1;
  }

  @Override
  public boolean contains(int element) {
    int index = indexOf(element);
    return index != -1;
  }

  public void print() {
    if (currentSize == 0) System.out.println("Empty Array");
    else {
      for (int i = 0; i < currentSize; i++) {
        System.out.println(this.myArray[i] + " ");
      }
      System.out.println();
    }
  }

  public int[] getMyArray() {
    return myArray;
  }

  public void setMyArray(int[] myArray) {
    this.myArray = myArray;
  }

  public void setCapacityIncrement(int capacityIncrement) {
    this.capacityIncrement = capacityIncrement;
  }

  @Override
  protected void finalize() throws Throwable {}

  @Override
  public String toString() {
    return "MyArray: " + Arrays.toString(myArray);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    MyArray copy = (MyArray) super.clone();
    int[] array = new int[currentCapacity];
    copy.setMyArray(array);
    copy.currentSize = 0;

    for (int i = 0; i < this.currentSize; i++) {
      copy.add(myArray[i]);
    }
    return copy;
  }
}
