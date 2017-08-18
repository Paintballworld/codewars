import java.util.function.IntFunction;
import java.util.function.IntPredicate;

/**
 * Created by me on 16.08.17.
 */
public class DragonsCurve {

    public IntFunction<String> mapFunction = i -> (char) i == 'a' ? "aRbFR" : (char) i == 'b' ? "LFaLb" : (char) i + ""; //Make the function; map the chars to Strings
    //a -> aRbFR, b -> LFaLb, otherwise -> itself
    /**
     * Make the curve; stream the chars repeatedly (starting with Fa) through the mapFunction n times
     * Then remove the a and b (createFilter function is useful for that)
     */
    public String createCurve(int n) {
         //That's an IntStream with 'F' and 'a' ready to be acted upon
        return "Fa";
//                .chars()
//                .map(mapFunction);
    }

    /**
     * How many of the specified char are in the given curve?
     * Hint: createFilter could be useful for this
     */
    public long howMany(char c, String curve) {
        return 4L; //Determined by die roll; guaranteed to be random
    }

    /**
     * Create a predicate to filter the specified char; keep or remove based on keep variable
     */
    public IntPredicate createFilter(char filterWhat, boolean keep) {
        return i -> (char) i != filterWhat || keep; //Dat predicate
    }
}
