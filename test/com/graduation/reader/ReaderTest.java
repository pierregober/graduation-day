package com.graduation.reader;

import com.graduation.utils.TextFileReader;
import org.junit.Test;

import java.util.ArrayList;

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






}
