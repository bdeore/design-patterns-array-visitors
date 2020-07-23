package arrayvisitors.driver;

import arrayvisitors._exceptions.EmptyInputFileException;
import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.Visitor;
import java.io.IOException;

/** @author Bhagwan Deore */
public class Driver {
  private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 6;

  public static void main(String[] args) {
    /*
     * As the build.xml specifies multiple arguments other than input and output, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 6) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
      System.err.printf(
          "Error: Incorrect number of arguments. Program accepts %d arguments (minimum).",
          REQUIRED_NUMBER_OF_CMDLINE_ARGS);
      System.exit(0);
    }

    try {

      /*
       * creating two instances of PopulateMyArrayVisitor. input file names are passed as the
       * argument to the constructor instance of File Processor is created internally.  */
      Visitor populateArrayVisitor_1 = new PopulateMyArrayVisitor(args[0]);
      Visitor populateArrayVisitor_2 = new PopulateMyArrayVisitor(args[1]);

      /*
       * creating two MyArray objects to store elements from these input files
       * MyArray API is very similar to that of an vector
       */
      MyArrayI myArray1 = new MyArray();
      MyArrayI myArray2 = new MyArray();

      /*
       *  PopulateMyArrayVisitor uses a while loop to read each element one line at a time and
       *  stores it in internal dynamic array.
       */
      myArray1.accept(populateArrayVisitor_1);
      myArray2.accept(populateArrayVisitor_2);

      /*
       * API for MyArrayList is very small as I have only included the necessary methods.
       * add method is used to store the populated MyArray objects in arrayList
       */
      MyArrayListI arrayList = new MyArrayList();
      arrayList.add(myArray1);
      arrayList.add(myArray2);

      // Instance of Results class to store output of visitors and write to the files
      // clearBuffer method deletes the old internal ArrayList and creates a new one.
      Results rs = new Results();

      // check README.md for details of algorithm used by CommonIntsVisitor and Time Complexity
      Visitor commonIntsVisitor = new CommonIntsVisitor(rs);
      arrayList.accept(commonIntsVisitor);
      rs.write(args[2]);
      rs.clearBuffer();

      // check README.md for details of algorithm used by MissingIntsVisitor and Time Complexity
      Visitor missingIntsVisitor = new MissingIntsVisitor(rs);
      myArray1.accept(missingIntsVisitor);
      rs.write(args[3]);
      rs.clearBuffer();

      myArray2.accept(missingIntsVisitor);
      rs.write(args[4]);
      rs.clearBuffer();

      /* MyLogger is a Singleton class which maintains a buffer for debug messages */
      MyLogger.getInstance().write(args[5]);

      // This code is for testing clone methods. To Check: make clone method in MyArrayList class
      // public and add CloneNotSupportedException to the catch clause below

      /*
      MyArray testCloning = ((MyArrayList) arrayList).getClone(myArray1);
      testCloning.add(98);
      testCloning.add(97);

      System.out.println("original unmodified MyArray");
      myArray1.print();

      System.out.println("Cloned and modified MyArray");
      testCloning.print();

      // test for the MyArrayList cloning feature
      MyArrayList testCloningList = (MyArrayList) ((MyArrayList) arrayList).clone();
      testCloningList.setMyArray(testCloning, 0);

      System.out.println("Original and unmodified MyArrayList - alterations don't affect original");
      arrayList.get(0).print();

      System.out.println("Cloned and modified MyArrayList");
      testCloningList.get(0).print();
      */

    } catch (IOException | EmptyInputFileException | InvalidADTException e) {
      System.out.println(e);
      System.out.println("(Driver Class) Terminating Program");
      System.exit(1);
    }
  }

  @Override
  public String toString() {
    return "Driver Class";
  }
}
