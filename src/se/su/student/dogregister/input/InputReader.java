/**
 * Martin Hansson, maha6445.
 */

package se.su.student.dogregister.input;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wrapper class for Scanner.
 * Defaults to System.in input
 * and uses specific prompt format when reading input.
 */
public class InputReader {

    private static final ArrayList<InputStream> ACTIVE_INPUT_STREAMS = new ArrayList<>();
    private final Scanner scanner;

    public InputReader(InputStream inputStream) {
        // If input stream is already used then throw exception.
        if (ACTIVE_INPUT_STREAMS.contains(inputStream))
            throw new IllegalStateException("Error: that input stream is already in use.");

        this.scanner = new Scanner(inputStream);
        ACTIVE_INPUT_STREAMS.add(inputStream);
    }

    public InputReader() {
        this(System.in);
    }

    /**
     * Prints the prompt and reads an integer from the scanner.
     *
     * @param prompt the prompt to print.
     * @return the integer value from the scanner.
     */
    public int readInt(String prompt) {
        System.out.print(prompt + "?> ");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    /**
     * Prints the prompt and reads a double from the scanner.
     *
     * @param prompt the prompt to print.
     * @return the double value from the scanner.
     */
    public double readDouble(String prompt) {
        System.out.print(prompt + "?> ");
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    /**
     * Prints the prompt and reads a String from the scanner.
     *
     * @param prompt the prompt to print.
     * @return the string value from the Scanner.
     */
    public String readString(String prompt) {
        System.out.print(prompt + "?> ");
        return scanner.nextLine();
    }

}
