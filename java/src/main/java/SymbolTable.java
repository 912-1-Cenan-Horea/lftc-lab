import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<String> symbols;

    public SymbolTable() {
        symbols = new ArrayList<String>();
    }

    public int add(String symbol) {
        if (!symbols.contains(symbol)) {
            symbols.add(symbol);
            return symbols.size() - 1;
        }
        return symbols.indexOf(symbol);
    }

    public String get(int position) {
        return symbols.get(position);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < symbols.size(); i++) {
            result.append(symbols.get(i)).append(": ").append(i).append("\n");
        }
        return result.toString();
    }
}