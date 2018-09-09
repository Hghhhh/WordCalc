package util;

import junit.framework.TestCase;
import model.MyFile;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtilTest extends TestCase {

    @Test
    public void testFileUtilt() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\1.txt");
        assertEquals(1,myFileList.size());
        assertEquals(7,myFileList.get(0).getLineNum());
        assertEquals(13,myFileList.get(0).getWordNum());
        assertEquals(4,myFileList.get(0).getBlankLineNum());
        assertEquals(62,myFileList.get(0).getCharNum());
    }

    @Test
    public void testFileUtilt2() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\1.txt");
        assertEquals(5,myFileList.get(0).getCodeLineNum());
        assertEquals(8,myFileList.get(0).getCommentLineNum());
        assertEquals("1.txt",myFileList.get(0).getFileName());
        System.out.println(myFileList.get(0).getFileName());
        System.out.println(myFileList.get(0).getLineNum());

    }

}