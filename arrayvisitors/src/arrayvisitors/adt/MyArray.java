package arrayvisitors.adt;

public class MyArray implements MyArrayI {

  private int[] myArray;
  private int currentSize;
  private int currentCapacity;

  public MyArray() {
    currentCapacity = 10;
    currentSize = 0;
    this.myArray = new int[currentCapacity];
  }

  @Override
  public void add(int element) {
    if (currentSize < currentCapacity) {
      myArray[currentSize] = element;
      currentSize++;
    } else {
      // reallocate
      System.out.println("Size Exceeded");
    }
  }

  @Override
  public void remove(int element) {}

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
  }

  @Override
  public void clear() {
    this.myArray = null;
    currentSize = 0;
  }

  public int[] getMyArray() {
    return myArray;
  }

  public void print() {
    for (int i = 0; i < currentSize; i++) {
      System.out.println(this.myArray[i] + " ");
    }
    System.out.println();
  }
}
