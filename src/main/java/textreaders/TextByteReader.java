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

    private List<Sentence> text = new ArrayList<>();
    private Sentence sentence = new Sentence();
    private Word word = new Word();

    @Override
    public TextOperator readText(String textToRead) {
        readFromCharArray(addTechSymbolToTheEnd(textToRead.toCharArray()));
        return getTextOperator();
    }

    protected void saveCharacterToText(char readerOutput) {
        if (checkPattern(readerOutput, SPACE)) {
            wordToSentence();
            word.addSymbol(new Punctuation(readerOutput));
            wordToSentence();
        }
        if (checkPattern(readerOutput, ENDING_PUNCTUATION)) {
            wordToSentence();
            sentenceToText();
            word.addSymbol(new Punctuation(readerOutput));
        }
        if (checkPattern(readerOutput, LETTER)) {
            word.addSymbol(new Letter(readerOutput));
        }
        if (checkPattern(readerOutput, "&")) {
            if (word.getSize() > 0 && checkPattern(lastLetterOfTheWord().getValue(), LETTER)) {
                wordToSentence();
            }
            sentenceToText();
        }
    }

    private Symbol lastLetterOfTheWord() {
        return word.getSymbols().get(word.getSize() - 1);
    }

    private char replaceToSpace(char spaceChar) {
        char result = ' ';
        if (spaceChar == ' ' || spaceChar == ',') {
            return result;
        }
        return spaceChar;
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
}
