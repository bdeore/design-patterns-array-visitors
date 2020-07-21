package arrayvisitors.driver;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;

/** @author Bhagwan Deore */
public class Driver {
  private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

  public static void main(String[] args) {
    /*
     * As the build.xml specifies multiple arguments other than input and output, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
      System.err.printf(
          "Error: Incorrect number of arguments. Program accepts %d arguments (minimum).",
          REQUIRED_NUMBER_OF_CMDLINE_ARGS);
      System.exit(0);
    }

    // testing myArrayList
    MyArrayI array = new MyArray();

    array.add(10);
    array.add(20);
    array.add(30);
    array.add(40);
    array.add(50);
    array.add(60);
    array.add(70);
    array.add(80);
    array.add(90);
    array.add(100);

    System.out.println("capacity : " + array.capacity());
    System.out.println("size : " + array.size());
    array.print();

    System.out.println("==============================");

    MyArrayI pArray = new MyArray(50);

    for (int i = 1; i <= 50; i++) {
      pArray.add(i);
    }

    System.out.println("capacity : " + pArray.capacity());
    System.out.println("size : " + pArray.size());
    pArray.print();

    MyArrayListI newList = new MyArrayList();
    newList.add(pArray);
    newList.add(array);

    newList.get(1).print();
  }
}
