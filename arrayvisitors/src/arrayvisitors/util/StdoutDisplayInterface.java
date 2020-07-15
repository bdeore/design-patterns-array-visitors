package arrayvisitors.util;

import arrayvisitors._exceptions.EmptyInputFileException;
import java.nio.file.InvalidPathException;

/** interface for standard out IO */
public interface StdoutDisplayInterface {

  void write() throws ArithmeticException, InvalidPathException, EmptyInputFileException;
}
