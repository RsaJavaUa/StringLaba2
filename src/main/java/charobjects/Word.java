package charobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class Word implements Comparable<Word> {
    private List<Symbol> symbols = new LinkedList<>();
    private Integer symbolCounter = 0;

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    public int getSize() {
        return symbols.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        symbols.forEach(result::append);
        return result.toString();
    }

    @Override
    public int compareTo(Word word) {
        if (symbolCounter.equals(word.symbolCounter) && word.symbolCounter > 0) {
            for (int i = 0; i < word.getSymbols().size(); i++) {
                if (getSymbols().get(i) != null) {
                    if (word.getSymbols().get(i).getValue() != getSymbols().get(i).getValue()) {
                        return word.getSymbols().get(i).getValue().compareTo(symbols.get(i).getValue());
                    }
                }
            }
        }
        return word.symbolCounter.compareTo(symbolCounter);
    }

    public void incrementCounter() {
        symbolCounter++;
    }
}
