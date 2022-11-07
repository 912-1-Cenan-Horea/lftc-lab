import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Parser {
    private String tokensPath;
    private String programPath;
    private ArrayList<String> operators = new ArrayList<String>();
    private ArrayList<String> separators = new ArrayList<String>();
    private ArrayList<String> reservedWords = new ArrayList<String>();
    private ArrayList<String> errors = new ArrayList<String>();
    List<AbstractMap.SimpleEntry<String, Integer>> PIF = new ArrayList<AbstractMap.SimpleEntry<String, Integer>>();
    SymbolTable symbolTable = new SymbolTable();
    SymbolTable constantsTable = new SymbolTable();


    public Parser(String tokens) throws FileNotFoundException {
        this.tokensPath = tokens;
        readTokensFromFile();

    }

    private void readTokensFromFile() throws FileNotFoundException {

        File tokens = new File("src/main/resources/" + tokensPath);


        Scanner scanner = new Scanner(tokens);
        String content = scanner.useDelimiter("\\Z").next();
        String[] split_tokens = content.split("---\r\n");
        for (int i = 0; i < split_tokens.length; i++)
            split_tokens[i] = split_tokens[i].replaceAll("\n\r", "");
        operators.addAll(List.of(split_tokens[0].split("\r\n")));
        separators.addAll(List.of(split_tokens[1].split("\r\n")));
        reservedWords.addAll(List.of(split_tokens[2].split("\r\n")));

    }

    public void parseFile(String programPath) throws FileNotFoundException {
        this.programPath = programPath;
        File program = new File("src/main/resources/" + programPath);
        Scanner scanner = new Scanner(program);
        String content = scanner.useDelimiter("\\Z").next();
        String[] lines = content.split("\r\n");
        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
            tokens= Arrays.stream(tokens).map(String::trim).toArray(String[]::new);
            for (int j = 0; j < tokens.length; j++) {
                if (operators.contains(tokens[j])) {
                    PIF.add(new AbstractMap.SimpleEntry<String, Integer>(tokens[j], -1));
                } else if (separators.contains(tokens[j])) {
                    PIF.add(new AbstractMap.SimpleEntry<String, Integer>(tokens[j], -1));
                } else if (reservedWords.contains(tokens[j])) {
                    PIF.add(new AbstractMap.SimpleEntry<String, Integer>(tokens[j], -1));
                } else if (isNumber(tokens[j]) || isStringConstant(tokens[j])) {
                    PIF.add(new AbstractMap.SimpleEntry<String, Integer>("constant", constantsTable.add(tokens[j])));
                } else if(isIdentifier(tokens[j])) {
                    PIF.add(new AbstractMap.SimpleEntry<String, Integer>("identifier", symbolTable.add(tokens[j])));
                }
                else {
                    errors.add("Invalid token: " + tokens[j]+" at line "+(i+1));
                }
            }

        }
    }
    private boolean isIdentifier(String token){
        return token.matches("[_a-zA-Z][_a-zA-Z0-9]{0,255}");
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isStringConstant(String string) {
        return string.startsWith("\"") && string.endsWith("\"");
    }

    private boolean isComposedIdentifier(String identifier) {
        return identifier.contains("(") && identifier.contains(")");
    }

    public void printPIF() {
        if (errors.size()>0)
        {
            System.out.println("Errors:");
            for (String error:errors)
                System.out.println(error);
            return;
        }
        for (AbstractMap.SimpleEntry<String, Integer> entry : PIF) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void printSymbolTable() {
        System.out.println("Symbol table:");
        System.out.println(symbolTable.toString());
    }

    public void printConstantsTable() {
        System.out.println("Constants table:");
        System.out.println(constantsTable.toString());
    }
}
