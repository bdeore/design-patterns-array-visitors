package arrayvisitors.adt;

import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.util.MyLogger;
import arrayvisitors.visitors.Visitor;
import java.util.Arrays;

/** MyArray class is an ADT that maintains an internal dynamic array of MyArray objects */
public class MyArrayList implements MyArrayListI, Cloneable {

  private int capacityIncrement;
  private MyArray[] myArrayList;
  private int currentSize;
  private int currentCapacity;

  /** No-Args constructor */
  public MyArrayList() {
    this(10);
  }

  /**
   * Parameterized constructor with option to specify initial capacity of the array
   *
   * @param initialCapacity array capacity at the time of creation
   */
  public MyArrayList(int initialCapacity) {
    currentCapacity = initialCapacity;
    currentSize = 0;
    this.myArrayList = new MyArray[currentCapacity];
    this.capacityIncrement = -1;
  }

  /**
   * Parameterized constructor with option to specify initial capacity of the array and a value of
   * increment in percentage of current size e.g. 50%, 200%
   *
   * @param initialCapacity array capacity at the time of creation
   * @param capacityIncrement percentage value of current capacity by which array should grow
   */
  public MyArrayList(int initialCapacity, int capacityIncrement) {
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
   * method to add MyArray objects to the array
   *
   * @param element two digit integer to be added to the array
   */
  @Override
  public void add(MyArrayI element) {
    if (currentSize < currentCapacity) {
      myArrayList[currentSize] = (MyArray) element;
      currentSize++;
    } else {
      int newCapacity =
          capacityIncrement == -1
              ? currentCapacity + (int) (0.5 * currentCapacity)
              : currentCapacity + (int) ((capacityIncrement / 100) * currentCapacity);

      ensureCapacity(newCapacity);
      MyLogger.getInstance().store("Array Size Exceeded");
      add(element);
    }
  }

  /**
   * method to retrieve value from the array based on index
   *
   * @param index index of the element to be retrieved
   * @return MyArray object
   */
  @Override
  public MyArray get(int index) {
    if (index <= currentSize && this.myArrayList != null) return this.myArrayList[index];
    return null;
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

    MyArray[] temp = new MyArray[minCapacity];

    for (int i = 0; i < currentSize; i++) {
      temp[i] = myArrayList[i];
    }

    this.myArrayList = temp;
    this.currentCapacity = minCapacity;
  }

  /** method to clear all the elements in array while maintaining current capacity */
  @Override
  public void clear() {
    this.myArrayList = null;
    this.myArrayList = new MyArray[currentCapacity];
    currentSize = 0;
  }

  /**
   * utility method to circumvent package access of clone method
   *
   * @param array to be cloned
   * @return cloned MyArray object
   * @throws CloneNotSupportedException exception
   */
  public MyArray getClone(MyArrayI array) throws CloneNotSupportedException {
    return (MyArray) ((MyArray) array).clone();
  }

  public void setMyArray(MyArrayI array, int index) {
    this.myArrayList[index] = (MyArray) array;
  }

  /**
   * getter for internal array list
   *
   * @return array of MyArray objects
   */
  public MyArray[] getMyArrayList() {
    return myArrayList;
  }

  /**
   * method to specify capacity increment percentage value for array list
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
   * method to make clone of the array list with deep copy
   *
   * @return MyArrayList Object
   * @throws CloneNotSupportedException exception
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    MyArrayList copy = (MyArrayList) super.clone();
    copy.myArrayList = new MyArray[this.currentCapacity];

    for (int i = 0; i < this.currentSize; i++) {
      copy.add((MyArrayI) ((this.myArrayList[i]).clone()));
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
