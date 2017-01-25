import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by jasonskipper on 1/25/17.
 */
public class TestRegEx {
    @Test
    public void testRegs(){
        String input = "2 34 4 1 99";
        String[] parts = input.split(" ");
        assertEquals("i was wrong",5, parts.length);
    }
}
