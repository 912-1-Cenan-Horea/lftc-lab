import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser("tokens.txt");
        parser.parseFile("program.txt");
        parser.printPIF();
        parser.printConstantsTable();

    }
}
