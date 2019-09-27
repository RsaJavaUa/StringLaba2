package textoperators;

import object.WordObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordsOperator {
    private static final String WORDS = "\\b";
    private static final String Punctuation = "\\pP";

    public void printSortingText(String text, char symbol) {
        List<WordObject> wordObjects = transformToObjects(splitText(text));
        countSymbolAndSort(wordObjects, symbol);
        printListOfWords(wordObjects);
    }

    List<String> splitText(String text) {
        return Arrays.stream(text.split(WORDS))
                .filter(word -> !word.equals(Punctuation))
                .filter(word -> !(word.contains(")") | word.contains(".") | word.contains("(")))
                .filter(word -> !(word.equals(" ") | word.equals(".\n")))
                .collect(Collectors.toList());
    }

    private List<WordObject> transformToObjects(List<String> stringList) {
        return stringList.stream()
                .map(String::toLowerCase)
                .map(WordObject::new)
                .collect(Collectors.toList());
    }

    private void countSymbols(List<WordObject> wordObjectList, char symbol) {
        wordObjectList.forEach(wordObject -> {
            char[] chars = wordObject.getWord().toCharArray();
            for (char letter : chars) {
                if (letter == symbol) {
                    wordObject.incrementCounter();
                }
            }
        });
    }

    private void sortList(List<WordObject> wordObjectList) {
        wordObjectList.sort(WordObject::compareTo);
    }

    void countSymbolAndSort(List<WordObject> wordObjectList, char symbol) {
        countSymbols(wordObjectList, symbol);
        sortList(wordObjectList);
    }

    private void printListOfWords(List<WordObject> wordObjectList) {
        wordObjectList.forEach(word -> System.out.print(word + " "));
    }
}
