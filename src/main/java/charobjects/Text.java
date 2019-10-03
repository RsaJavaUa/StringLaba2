package charobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Text {
    private List<Sentence> sentences = new ArrayList<>();

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        sentences.forEach(result::append);
        return result.toString();
    }
}

