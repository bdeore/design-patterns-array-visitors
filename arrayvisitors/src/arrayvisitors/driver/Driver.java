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

    System.out.println("hello world!");

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
    array.add(10);
    array.add(10);

    System.out.println(array.get(0));
    System.out.println(array.get(4));

    System.out.println("==============================");
    array.print();
  }
}
