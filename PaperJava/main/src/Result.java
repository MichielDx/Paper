/**
 * Created by Arthur Haelterman on 27/01/2017.
 */
public class Result {
    private final String word;
    private final int vowelcount;

    public Result(String word, int vowelcount) {
        this.word = word;
        this.vowelcount = vowelcount;
    }

    public String getWord() {
        return word;
    }

    public int getVowelcount() {
        return vowelcount;
    }
}
