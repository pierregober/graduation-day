package com.graduation.reader;

import com.graduation.elements.Player;
import com.graduation.utils.Grade;
import com.graduation.utils.TextFileReader;
import com.graduation.utils.readMap;
import org.junit.Test;

import java.util.ArrayList;

import static com.graduation.utils.Grade.*;
import static org.junit.Assert.*;

public class ReaderTest {


    @Test
    public void testFileReaderNotEmpty() throws Exception {
        TextFileReader tfr = new TextFileReader();
        tfr.readFightFile();
        assertNotNull(tfr.readFightFile());
    }

    @Test
    public void testFileReaderArraListSize() throws Exception {
        TextFileReader tfr = new TextFileReader();
        tfr.readFightFile();
        assertNotEquals(tfr.readFightFile().size(),0);
    }

    @Test
    public void testFileReaderIsEmptyArrayList() throws Exception {
        TextFileReader tfr = new TextFileReader();
        tfr.readFightFile();
        assertFalse(tfr.readFightFile().isEmpty());
    }

    @Test
    public void testFileReaderMatcherForArraySamesys() throws Exception {
        TextFileReader tfr = new TextFileReader();
        TextFileReader tfr2 = new TextFileReader();
        assertEquals(tfr.readFightFile(),tfr2.readFightFile());
    }

    @Test
    public void testReadMapwithNormalObject() throws Exception{
        Player player = new Player("joe", 2, 5,JUNIOR,"Maryland");
        assertFalse(readMap.convertedMap().isEmpty());
    }
    @Test
    public void testReadMapwithNegativeIntsObject() throws Exception{
        Player player = new Player("joe", -2, -5,JUNIOR,"Maryland");
        assertFalse(readMap.convertedMap().isEmpty());
    }
    @Test
    public void testReadMapwithNegativeManyObjects() throws Exception{
        Player player1 = new Player("joe", -2, -5,JUNIOR,"Maryland");
        Player player2 = new Player("mary", -4, 7,SOPHOMORE,"Maryland");
        Player player3 = new Player("mary-joe", -2, 95,SENIOR,"Maryland");
        Player player4 = new Player("nancy", -2, -5,JUNIOR,"Maryland");
        Player player5 = new Player("john-smith", -2, -5,JUNIOR,"Maryland");

        assertFalse(readMap.convertedMap().isEmpty());
    }







}
