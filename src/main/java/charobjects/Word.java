package charobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class Word implements Comparable<Word>{
    private List<Symbol> symbols = new LinkedList<>();
    private Integer symbolCounter = 0;

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    public int getSize(){
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
        return word.symbolCounter.compareTo(symbolCounter);
    }

    public void incrementCounter(){
        symbolCounter++;
    }
}
