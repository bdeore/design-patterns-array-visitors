package arrayvisitors.visitors;

import arrayvisitors._exceptions.EmptyInputFileException;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import java.io.IOException;

public class PopulateMyArrayVisitor implements Visitor {

  private final String fileName;
  private final FileProcessor fp;

  public PopulateMyArrayVisitor(String fileName) throws IOException {
    this.fileName = fileName;
    this.fp = new FileProcessor(fileName);
  }

  @Override
  public void visit(MyArrayI myArray) {
    int count = 0;
    try {
      String line = fp.poll();
      while (line != null) {
        int digit = Integer.parseInt(line);
        myArray.add(digit);
        count++;
        line = fp.poll();
      }
      if (count == 0) throw new EmptyInputFileException();
    } catch (NumberFormatException e) {
      System.out.println(
          "Invalid word found in input file : [ "
              + this.fileName
              + " ] : Line Number => "
              + (count + 1));
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    } catch (IOException | NullPointerException | EmptyInputFileException e) {
      System.out.println(e);
      System.out.println("Terminating Program");
      System.exit(1);
    }
  }

  @Override
  public void visit(MyArrayListI myArrayList) {
    // throw not supported exception
  }
}
