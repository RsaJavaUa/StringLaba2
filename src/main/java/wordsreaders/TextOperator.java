package wordsreaders;

import charobjects.Sentence;
import charobjects.Symbol;
import charobjects.Text;
import charobjects.Word;
import lombok.NoArgsConstructor;
import util.PatternChecker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TextOperator {
    private final static String SPACES_AND_PUNCTUATION = "[//s.!?,\" \"\n]"; //" "

    public TextOperator(Text text) {
        this.text = text;
    }

    private Text text;
    private Comparator<Word> comparator = Word::compareTo;

    public void printText() {
        text.getSentences().forEach(System.out::print);
    }

    public List<Word> wordsWithoutPunctuation() {
        List<Word> result = new ArrayList<>();
        text.getSentences().stream().map(Sentence::getWords).forEach(result::addAll);
        return ignorePunctuation(result);
    }

    public List<Word> sortBySymbol(char symbol) {
        List<Word> words = wordsWithoutPunctuation();
        words.forEach(word -> {
            word.getSymbols().forEach(symb -> {
                if (symb.getValue() == symbol) {
                    word.incrementCounter();
                }
            });
        });
        words.sort(comparator);
        words.forEach(word -> word.addSymbol(new Symbol(' ')));
        return words;
    }

    private List<Word> ignorePunctuation(List<Word> listWords) {
        return listWords.stream()
                .filter(word -> notAPunctuation.test(word)).collect(Collectors.toList());
    }

    private Predicate<Word> notAPunctuation = word -> {
        if (word.getSize() == 0) {
            return false;
        } else return !(word.getSize() <= 1
                & PatternChecker.checkPattern(word.getSymbols().get(0).getValue(), SPACES_AND_PUNCTUATION));
    };
}
