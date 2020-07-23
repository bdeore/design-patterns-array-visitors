package arrayvisitors.adt;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.util.MyLogger;
import arrayvisitors.visitors.Visitor;
import java.util.Arrays;

/** MyArray class is an ADT that maintains an internal dynamic array */
public class MyArray implements MyArrayI, Cloneable {

  private int capacityIncrement;
  private int[] myArray;
  private int currentSize;
  private int currentCapacity;

  /** No-Args constructor */
  public MyArray() {
    this(10);
  }

  /**
   * Parameterized constructor with option to specify initial capacity of the array
   *
   * @param initialCapacity array capacity at the time of creation
   */
  public MyArray(int initialCapacity) {
    currentCapacity = initialCapacity;
    currentSize = 0;
    this.myArray = new int[currentCapacity];
    this.capacityIncrement = -1;
  }

  /**
   * Parameterized constructor with option to specify initial capacity of the array and a value of
   * increment in percentage of current size e.g. 50%, 200%
   *
   * @param initialCapacity array capacity at the time of creation
   * @param capacityIncrement percentage value of current capacity by which array should grow
   */
  public MyArray(int initialCapacity, int capacityIncrement) {
    this(initialCapacity);
    this.capacityIncrement = capacityIncrement;
  }

  /**
   * method to accept the visitor
   *
   * @param v visitor
   * @throws InvalidADTException user defined exception thrown on ADT mismatch
   */
  @Override
  public void accept(Visitor v) throws InvalidADTException {
    v.visit(this);
  }

  /**
   * method to add int elements to the array
   *
   * @param element two digit integer to be added to the array
   */
  @Override
  public void add(int element) {
    if (currentSize < currentCapacity) {
      myArray[currentSize] = element;
      currentSize++;
    } else {
      int newCapacity =
          capacityIncrement == -1
              ? currentCapacity + (int) (0.5 * currentCapacity)
              : currentCapacity + (int) ((capacityIncrement / 100) * currentCapacity);

      ensureCapacity(newCapacity);
      MyLogger.getInstance().store("Size Exceeded :: New capacity : " + newCapacity);
      add(element);
    }
  }

  /**
   * method to remove element from array
   *
   * @param element two digit integer to be removed from the array
   */
  @Override
  public void remove(int element) {
    int elementIndex = indexOf(element);

    if (elementIndex == -1) MyLogger.getInstance().store("Element doesn't exist in the array");
    else {
      for (int i = elementIndex; i < currentSize; i++) myArray[i] = myArray[i + 1];
      currentSize--;
    }
  }

  /**
   * method to retrieve value from the array based on index
   *
   * @param index index of the element to be retrieved
   * @return two digit integer
   */
  @Override
  public int get(int index) {
    if (index <= currentSize && this.myArray != null) return this.myArray[index];
    return -1;
  }

  /**
   * method to get current count of total elements in the array
   *
   * @return positive integer representing the size of array
   */
  @Override
  public int size() {
    return currentSize;
  }

  /**
   * method to get max capacity of the array
   *
   * @return positive integer representing the max size of array
   */
  @Override
  public int capacity() {
    return currentCapacity;
  }

  /**
   * method to reallocate space when array runs out of memory
   *
   * @param minCapacity new capacity value
   */
  @Override
  public void ensureCapacity(int minCapacity) {
    int[] temp = new int[minCapacity];

    for (int i = 0; i < currentSize; i++) {
      temp[i] = myArray[i];
    }

    this.myArray = temp;
    this.currentCapacity = minCapacity;
  }

  /** method to clear all the elements in array while maintaining current capacity */
  @Override
  public void clear() {
    this.myArray = null;
    this.myArray = new int[currentCapacity];
    currentSize = 0;
  }

  /**
   * method to find index of element
   *
   * @param element integer whose index needs to be found
   * @return index value
   */
  @Override
  public int indexOf(int element) {
    for (int i = 0; i < currentSize; i++) {
      if (myArray[i] == element) return i;
    }
    return -1;
  }

  /**
   * method to check if element exists in the array
   *
   * @param element integer to be searched
   * @return true or false value
   */
  @Override
  public boolean contains(int element) {
    int index = indexOf(element);
    return index != -1;
  }

  /** utility method to print all the elements in array to debug file on separate lines */
  public void print() {
    if (currentSize == 0) MyLogger.getInstance().store("Empty Array");
    else {
      for (int i = 0; i < currentSize; i++) {
        MyLogger.getInstance().store(this.myArray[i] + " ");
      }
      MyLogger.getInstance().store("\n");
    }
  }

  /**
   * getter for internal array
   *
   * @return array of integers
   */
  public int[] getMyArray() {
    return myArray;
  }

  /**
   * method to set internal array
   *
   * @param myArray new array
   */
  public void setMyArray(int[] myArray) {
    this.myArray = myArray;
  }

  /**
   * method to specify capacity increment percentage value for array
   *
   * @param capacityIncrement increment percentage value
   */
  public void setCapacityIncrement(int capacityIncrement) {
    this.capacityIncrement = capacityIncrement;
  }

  /**
   * empty finalize method
   *
   * @throws Throwable exception
   */
  @Override
  protected void finalize() throws Throwable {}

  /**
   * method to make clone of the array with deep copy
   *
   * @return MyArray Object
   * @throws CloneNotSupportedException exception
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    MyArray copy = (MyArray) super.clone();
    copy.myArray = new int[this.currentCapacity];
    copy.currentSize = 0;

    for (int i = 0; i < this.currentSize; i++) {
      copy.add(myArray[i]);
    }
    return copy;
  }

  /**
   * toString method for debugging
   *
   * @return String
   */
  @Override
  public String toString() {
    return "MyArray: " + Arrays.toString(myArray);
  }
}
