import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ROT13Test {


    @Test
    public void rotateStringTest0() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "ABCDEF";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'A');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest1() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "DEFABC";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'D');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest2() {
        // Given
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "NOPQRSTUVWXYZABCDEFGHIJKLM";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'N');
        System.out.println(s1);
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void cryptTest1() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Jul qvq gur puvpxra pebff gur ebnq?";

        String Q2 = "Gb trg gb gur bgure fvqr!";
        String A2 = "To get to the other side!";

        // When
        String actual = cipher.encrypt(Q1);
        System.out.println(Q1);
        System.out.println(A1);
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(A1));

        // When
        String actual2 = cipher.decrypt(Q2);
        System.out.println(Q2);
        System.out.println(A2);
        System.out.println(actual2);
        // Then
        assertTrue(actual2.equals(A2));
    }
    @Test
    public void cryptTest2() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        System.out.println(Q1);
        System.out.println(cipher.crypt(Q1));

        // When
        String actual = cipher.crypt(cipher.crypt(Q1));

        System.out.println(actual);
        // Then
        assertTrue(actual.equals(Q1));
    }
    @Test
    public void cryptTest3() {
        // Given
        ROT13 cipher = new ROT13('a', 'b');

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Xiz eje uif dijdlfo dsptt uif spbe?";

        String Q2 = "Up hfu up uif puifs tjef!";
        String A2 = "To get to the other side!";

        // When
        System.out.println(cipher.encrypt(Q1));
        String actual = cipher.crypt(cipher.crypt(Q1));
        // Then
        assertFalse(actual.equals(A1));

        // When

        String actual2 = cipher.decrypt(Q2);
        System.out.println(Q2);
        System.out.println(A2);
        System.out.println(actual2);
        // Then
        assertTrue(actual2.equals(A2));
    }

    @Test
    public void testLoadFile() throws IOException {
        // Given
        //ArrayList<String> actual = new ArrayList<>();
        ROT13 cipher = new ROT13();

        // When
        //  actual = cipher.loadDataFromFile("sonnet18.txt");
        cipher.loadDataFromFile("sonnet18.txt");
        System.out.println(cipher.fileStrings.get(0));

        // Then
        Assert.assertNotNull(cipher.fileStrings);
    }

    // only created to test file creation
    @Test
    public void testnewFile() throws IOException {
        // Given
        //ArrayList<String> actual = new ArrayList<>();
        ROT13 cipher = new ROT13();

        // When
      //  cipher.loadDataFromFile("sonnet18.txt");

        cipher.printDataFromFile("sonnet18.enc");
      // System.out.println(cipher.fileStrings.get(0));

        // Then
        Assert.assertNotNull(cipher.fileStrings);
    }

    @Test
    public void testFileEncrpyt() throws IOException {
        // Given
        ROT13 cipher = new ROT13('a','n');
        cipher.loadDataFromFile("sonnet18.txt");

        // When
        cipher.encryptLoadedFile();
        cipher.printDataFromFile("sonnet18.enc");
        cipher.decryptLoadedFile();

        // Then
        Assert.assertEquals(cipher.fileStrings,cipher.fileStringsDecrypt );




    }


}