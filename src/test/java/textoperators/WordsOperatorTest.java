package textoperators;

import object.WordObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordsOperatorTest {

    private final static String TEXT = "String toString This method will " +
            "return the String representation of the Pair.";

    private WordsOperator wordsOperator = new WordsOperator();
    private List<String> list;
    private List<WordObject> stringList = new ArrayList<>();

    @Before
    public void setUp() {
        list = wordsOperator.splitText(TEXT);
        stringList.add(new WordObject("method"));
        stringList.add(new WordObject("representation"));
        stringList.add(new WordObject("return"));

    }

    @Test
    public void splitTextShouldReturnTwelve() {
        assertEquals(12, list.size());
    }

    @Test
    public void splitTextFirstAndLastWord() {
        assertEquals("String", list.get(0));
        assertEquals("Pair", list.get(list.size() - 1));
    }

    @Test
    public void sortByLetterE() {
        wordsOperator.countSymbolAndSort(stringList,'e');
        assertEquals("representation", stringList.get(0).getWord());
    }

}