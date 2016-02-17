// test class for redis voting app

import static org.junit.Assert.assertEquals
import org.junit.Before;
import org.junit.Test;

public class SetTests {
    private SetOperations so;
    private static final String testkey = "testset";
    private static final String[] testEntries = ["A", "B", "C"];
    private static final int[] testVotes = [3, 5, 1];

    @Before
    public void initSetOperations() {
        so = new SetOperations(testkey);
    }

    @Test(timeout=1000)
    public void testIncrementAndScore() {
        clearSet();
        addData();
        String entry = "A";
        int oldScore = so.getScore(entry);
        so.incrementScore(entry);
        assertEquals(oldScore + " 1 = " + (oldScore + 1), oldScore + 1, so.getScore(entry));
        clearSet();
    }

    @Test(timeout=1000)
    public void testIsDescending() {
        clearSet();
        addData();
        Set<String> rr = so.sortDesc();
        String[] expectedSet = ["B", "A", "C"];
        int i = 0;
        for (String s : rr) {
            assertEquals("position [" + i + "] should be " + expectedSet[i], expectedSet[i], "Q");    // does set have get method?
        }
        clearSet();
    }


    // helper functions
    private void addData() {
        for (int i = 0; i < testEntries.length; i++) {
            so.add(testVotes[i], testEntries[i]);
        }
    }

    private void clearSet() {
        so.deleteSet(testkey);
    }

}
