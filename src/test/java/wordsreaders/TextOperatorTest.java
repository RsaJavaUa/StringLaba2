package wordsreaders;

import charobjects.Word;
import org.junit.Before;
import org.junit.Test;
import textreaders.TextByteReader;

import java.util.List;

import static org.junit.Assert.*;

public class TextOperatorTest {
    private final static String TEXT_STRING = "String toString This method will " +
            "return the String representation of the Pair.";
    private TextOperator textOperator;
    private TextByteReader textByteReader = new TextByteReader();
    private Integer sizeBefore;

    @Before
    public void setUp () {
        textOperator = textByteReader.readText(TEXT_STRING);
        sizeBefore = TEXT_STRING.split(" ").length;
    }

    @Test
    public void wordsWithoutPunctuation() {
        List<Word> words = textOperator.wordsWithoutPunctuation();
        words.forEach(System.out::println);
        assertEquals(sizeBefore-1, words.size());
    }

    @Test
    public void sortBySymbol() {
    }
}