package util;

import junit.framework.TestCase;
import main.Main;
import model.MyFile;
import org.junit.Test;
import view.View;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtilTest extends TestCase {

    @Test
    public void testFile1() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test\\HelloWorld.java");
        assertEquals(1,myFileList.size());
        assertEquals(11,myFileList.get(0).getLineNum());
        assertEquals(12,myFileList.get(0).getWordNum());
        assertEquals(3,myFileList.get(0).getBlankLineNum());
        assertEquals(103,myFileList.get(0).getCharNum());
        assertEquals(5,myFileList.get(0).getCommentLineNum());
    }

    @Test
    public void testFile2() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test\\OneChar.java");
        assertEquals(1,myFileList.size());
        assertEquals(1,myFileList.get(0).getLineNum());
        assertEquals(1,myFileList.get(0).getWordNum());
        assertEquals(0,myFileList.get(0).getBlankLineNum());
        assertEquals(1,myFileList.get(0).getCharNum());
        assertEquals(0,myFileList.get(0).getCommentLineNum());
    }

    public void testFile3() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test\\BlankFile.java");
        assertEquals(1,myFileList.size());
        assertEquals(0,myFileList.get(0).getLineNum());
        assertEquals(0,myFileList.get(0).getWordNum());
        assertEquals(0,myFileList.get(0).getBlankLineNum());
        assertEquals(0,myFileList.get(0).getCharNum());
        assertEquals(0,myFileList.get(0).getCommentLineNum());
    }

    public void testFile4() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test\\OneWord.java");
        assertEquals(1,myFileList.size());
        assertEquals(1,myFileList.get(0).getLineNum());
        assertEquals(1,myFileList.get(0).getWordNum());
        assertEquals(0,myFileList.get(0).getBlankLineNum());
        assertEquals(5,myFileList.get(0).getCharNum());
        assertEquals(0,myFileList.get(0).getCommentLineNum());
    }

    public void testFile6() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test\\OneLine.java");
        assertEquals(1,myFileList.size());
        assertEquals(1,myFileList.get(0).getLineNum());
        assertEquals(8,myFileList.get(0).getWordNum());
        assertEquals(0,myFileList.get(0).getBlankLineNum());
        assertEquals(27,myFileList.get(0).getCharNum());
        assertEquals(0,myFileList.get(0).getCommentLineNum());
    }

    public void testDir() throws IOException {
        List<MyFile> myFileList =  FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\test");
        assertEquals(5,myFileList.size());
    }

    @Test
    public void testMain() throws IOException {
        String[] strings = {"-s","-a","C:\\Users\\Administrator\\Desktop\\*.java"};
        Main.main(strings);

    }



}