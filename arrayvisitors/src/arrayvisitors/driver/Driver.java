package arrayvisitors.driver;

import arrayvisitors._exceptions.EmptyInputFileException;
import arrayvisitors._exceptions.InvalidADTException;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
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

      // Test for PopulateMyArrayVisitor
      Visitor populateArrayVisitor_1 = new PopulateMyArrayVisitor(args[0]);
      Visitor populateArrayVisitor_2 = new PopulateMyArrayVisitor(args[1]);

      MyArrayI myArray1 = new MyArray();
      MyArrayI myArray2 = new MyArray();

      myArray1.accept(populateArrayVisitor_1);
      myArray2.accept(populateArrayVisitor_2);

      // Test for CommonIntsVisitor
      MyArrayListI arrayList = new MyArrayList();
      arrayList.add(myArray1);
      arrayList.add(myArray2);

      Results rs = new Results();

      Visitor commonIntsVisitor = new CommonIntsVisitor(rs);

      arrayList.accept(commonIntsVisitor);
      rs.write(args[2]);
      rs.clearBuffer();

      // Test for MissingIntsVisitor
      Visitor missingIntsVisitor = new MissingIntsVisitor(rs);

      myArray1.accept(missingIntsVisitor);
      rs.write(args[3]);
      rs.clearBuffer();

      myArray2.accept(missingIntsVisitor);
      rs.write(args[4]);
      rs.clearBuffer();

      // todo

      // test for the MyArray cloning feature
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

    } catch (IOException
        | EmptyInputFileException
        | CloneNotSupportedException
        | InvalidADTException e) {
      System.out.println(e);
      System.out.println("(Driver Class) Terminating Program");
      System.exit(1);
    }
  }
}
