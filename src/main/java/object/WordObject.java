package object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordObject implements Comparable<WordObject> {

    public WordObject(String word) {
        this.word = word;
    }

    private String word;
    private Integer counter=0;

    @Override
    public int compareTo(WordObject wordObject) {
        return wordObject.counter.compareTo(counter);
    }

    @Override
    public String toString() {
        return word;
    }

    public void incrementCounter(){
        counter++;
    }
}
