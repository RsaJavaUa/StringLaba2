import textreaders.TextByteReader;
import wordsreaders.TextOperator;

public class ApplicationStart {

    static String text = "Pair (K key, V value) : Creates a new pair.\n"+
            "boolean equals() : It is used to compare two pair objects. ...\n"+
            "String toString() : This method will return the String representation of the Pair.\n"+
            "K getKey() : It returns key for the pair.\n"+
            "V getValue() : It returns value for the p";
    public static void main(String[] args) {

        TextByteReader textByteReader = new TextByteReader();
        textByteReader.readText(text);
        TextOperator textOperator = textByteReader.getTextOperator();
        textOperator.printText();
        System.out.println("\n**********");
        //is Done
        textOperator.sortBySymbol('r').forEach(a -> System.out.print(a));
    }
}
