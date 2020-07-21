package arrayvisitors.driver;

import arrayvisitors.adt.MyArray;

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

    // Testing myArray
    MyArray array = new MyArray();

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

    array.add(10);
    array.add(10);

    System.out.println("capacity : " + array.capacity());
    System.out.println("size : " + array.size());

    System.out.println(array.get(0));
    System.out.println(array.get(4));

    System.out.println("==============================");
    array.print();

    array.clear();

    array.add(50);
    array.add(60);
    array.add(70);
    array.add(80);
    array.add(90);
    array.add(100);

    array.print();
    System.out.println("capacity : " + array.capacity());
    System.out.println("size : " + array.size());

    System.out.println(array.indexOf(50));
    System.out.println(array.indexOf(70));
    System.out.println(array.indexOf(100));

    array.remove(100);
    array.print();

    array.remove(50);
    array.print();

    array.remove(70);
    array.print();

    System.out.println(array.contains(70));
    System.out.println(array.contains(60));

    // testing parameterized constructors

    // with specified initial capacity
    MyArray pArray = new MyArray(50);
    System.out.println(pArray.capacity());
    for (int i = 1; i <= 50; i++) {
      pArray.add(i);
    }
    pArray.print();

    // with specified initial capacity and increment
    MyArray test = new MyArray(5, 20);
    System.out.println("capacity : " + test.capacity());
    System.out.println("size : " + test.size());
    for (int i = 1; i <= 6; i++) {
      test.add(i);
    }
    System.out.println("capacity : " + test.capacity());
    System.out.println("size : " + test.size());

    System.out.println(test.toString());
  }
}
