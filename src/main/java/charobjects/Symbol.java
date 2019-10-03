package charobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symbol implements Comparable <Symbol>{
    private Character value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Symbol symbol) {
        return symbol.getValue().compareTo(value);
    }
}
