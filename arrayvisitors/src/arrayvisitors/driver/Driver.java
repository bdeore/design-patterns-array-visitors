package arrayvisitors.driver;

import arrayvisitors._exceptions.EmptyInputFileException;
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
      Visitor array_0_visitor = new PopulateMyArrayVisitor(args[0]);
      Visitor array_1_visitor = new PopulateMyArrayVisitor(args[1]);

      MyArrayI array_0 = new MyArray();
      MyArrayI array_1 = new MyArray();

      array_0.accept(array_0_visitor);
      array_1.accept(array_1_visitor);

      System.out.println("capacity : " + array_0.capacity());
      System.out.println("size : " + array_0.size());
      array_0.print();

      System.out.println("capacity : " + array_1.capacity());
      System.out.println("size : " + array_1.size());
      array_1.print();

      // Test for CommonIntsVisitor
      MyArrayListI arrayList = new MyArrayList();
      arrayList.add(array_0);
      arrayList.add(array_1);

      Results rs = new Results();
      Visitor commonIntsVisitor = new CommonIntsVisitor(rs);

      arrayList.accept(commonIntsVisitor);
      rs.write(args[2]);

      // Test for MissingIntsVisitor
      rs = new Results();
      Visitor missingIntsVisitor = new MissingIntsVisitor(rs);
      array_0.accept(missingIntsVisitor);
      rs.write(args[3]);

      rs = new Results();
      missingIntsVisitor = new MissingIntsVisitor(rs);
      array_1.accept(missingIntsVisitor);
      rs.write(args[4]);

    } catch (IOException | EmptyInputFileException e) {
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    }
  }
}
