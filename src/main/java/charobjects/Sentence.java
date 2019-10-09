package charobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Sentence {
    private List <Word> words = new LinkedList<>();

    public void addWord(Word word){
        words.add(word);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        words.forEach(result::append);
        return result.toString();
    }
}
