package org.rostik.andrusiv;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.rostik.andrusiv.analyzer.BaseTest;
import org.rostik.andrusiv.analyzer.client.Main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void byMostCharsMainTestHappyPath()
    {
        Main.main(new String[] {"1", "test/"});
        assertTrue(baos.toString().contains("Path To File By Most S Character Repeat: test\\4s\\4zssss.txt"));
    }

    @Test
    public void dividedByLettersMainTestHappyPath()
    {
        Main.main(new String[] {"2", "test/"});

        assertTrue(baos.toString().contains("divided by first letters: {0=2, 1=1, 2=1, 3=1, 4=1}"));

    }

    @Test
    public void sortedBySizeMainTestHappyPath()
    {
        Main.main(new String[] {"3", "test/"});
        assertTrue(baos.toString().contains("files Sorted By Size: [0pqdsrrrr.txt, 0rrrrr.txt, 4zssss.txt, 3bsss.txt, 2ass.txt]"));
    }

    @Test
    public void avgFileSizeMainTestHappyPath()
    {
        Main.main(new String[] {"4", "test/"});
        assertTrue(baos.toString().contains("avg file size: 3584.0"));
    }

    @Test
    public void wrongCommandNumberMainTestUnhappyPath()
    {
        Main.main(new String[] {"15", "test/"});
        assertTrue(baos.toString().contains("command was not found"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void argsNotValidMainTestUnhappyPath()
    {
        Main.main(new String[] {"zxc", "zxc"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void argsOnlyOneParamMainTestUnhappyPath()
    {
        Main.main(new String[] {"zxc"});
    }

}
