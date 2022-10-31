public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.add("a");
        symbolTable.add("b");
        symbolTable.add("c");
        symbolTable.add("a");
        System.out.println(symbolTable);
    }
}
