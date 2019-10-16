package textreaders;

import charobjects.Letter;
import charobjects.Punctuation;
import charobjects.Sentence;
import charobjects.Symbol;
import charobjects.Text;
import charobjects.Word;
import wordsreaders.TextOperator;

import java.util.ArrayList;
import java.util.List;

import static util.PatternChecker.checkPattern;

public class TextByteReader extends textreaders.TextReader {

    private final static String LETTER = "\\w";
    private final static String SPACE = "[\\s|,]";
    private final static String ENDING_PUNCTUATION = "[.!?]";
    private final static Character SPACE_CHAR = ' ';

    private List<Sentence> text = new ArrayList<>();
    private Sentence sentence = new Sentence();
    private Word word = new Word();

    @Override
    public TextOperator readText(String textToRead) {
        readFromCharArray(addTechSymbolToTheEnd(ignoreCase(textToRead).toCharArray()));
        return getTextOperator();
    }

    protected void saveCharacterToText(char readerOutput) {
        if (checkPattern(readerOutput, SPACE)) {
            addWordAddToSentence(readerOutput);
        }
        if (checkPattern(readerOutput, ENDING_PUNCTUATION)) {
            addSentenceToText(readerOutput);
        }
        if (checkPattern(readerOutput, LETTER)) {
            word.addSymbol(new Letter(readerOutput));
        }
        if (checkPattern(readerOutput, "&")) {
            addLastSentenceToText();
        }
    }

    private void addLastSentenceToText() {
        if (word.getSize() > 0 && checkPattern(lastLetterOfTheWord().getValue(), LETTER)) {
            wordToSentence();
        }
        sentenceToText();
    }

    private void addSentenceToText(char readerOutput) {
        wordToSentence();
        sentenceToText();
        word.addSymbol(new Punctuation(readerOutput));
    }

    private void addWordAddToSentence(char readerOutput) {
        wordToSentence();
        word.addSymbol(new Punctuation(readerOutput));
        wordToSentence();
    }

    private Symbol lastLetterOfTheWord() {
        return word.getSymbols().get(word.getSize() - 1);
    }

    private void sentenceToText() {
        text.add(sentence);
        sentence = new Sentence();
    }

    private void wordToSentence() {
        sentence.addWord(word);
        word = new Word();
    }

    protected Text getText() {
        return new Text(text);
    }

    public TextOperator getTextOperator() {
        return new TextOperator(getText());
    }

    private void readFromCharArray(char[] chars) {
        for (char symbol : chars) {
            saveCharacterToText(symbol);
        }
    }

    private char[] addTechSymbolToTheEnd(char[] array) {
        char[] result = new char[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[result.length - 1] = '&';
        return result;
    }

    private String ignoreCase(String string) {
        return string.toLowerCase();
    }
}
