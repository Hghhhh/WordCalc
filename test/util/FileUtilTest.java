package util;

import junit.framework.TestCase;
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

    @Test
    public void testFileUtilt3() throws IOException {
       // WordCalc wordCalc = new WordCalc();
       // wordCalc.setTextForView("C:\\Users\\Administrator\\Documents\\.metadata\\version.ini");
        //Path path = FileSystems.getDefault().getPath("C:\\Users\\Administrator\\Desktop\\123\\1.txt");
        String s = "*asdas";
        String[] ss = s.split("\\*");
        for(int i=0;i<ss.length;i++){
            System.out.println(ss[i]+"  "+ss.length);
        }
    }


}