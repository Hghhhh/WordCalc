package util;

import model.MyFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类：根据文件名读取输入的文件
 */
public class FileUtil {
    public static final Pattern commentLinePattern = Pattern.compile("^//.*$|^/\\*.*$|^\\*.*$|^\\*/.*$");
    public static final Pattern blankLinePattern = Pattern.compile("[{}.,:]{1}");

    public static List<MyFile> readFile(String fileName) throws IOException {
        List<MyFile> myFiles = new ArrayList<MyFile>();
        Path path = FileSystems.getDefault().getPath(fileName);
        //判断文件路径是否是文件夹
        if(!Files.isDirectory(path)){
            myFiles.add(singleFile(path));
        }
        else{
            List<Path> paths = listSourceFiles(path);
            for(Path p : paths){
                myFiles.add(singleFile(p));
            }
        }
        return myFiles;
    }


    /**
     * 处理单个文件的路径
     * @param path
     * @return MyFile实例
     */
    public static MyFile singleFile(Path path) throws IOException {
        MyFile myFile = new MyFile();
        myFile.setFileName(path.getFileName().toString());
        StringBuilder fileMessage = new StringBuilder();
        //这里略坑，utf8编码会因为文件里面有中文而报错，改成GBK编码就ok了
        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("GBK"));
        String line = null;
        int lineNum = 0;
        int charNum = 0;
        int wordNum = 0;
        int commentLine=0;
        int blankLine = 0;
        Matcher matcher = null;
        //读取文件
        while((line = reader.readLine())!=null){
            lineNum++;
            String lineTemp = line.trim();//判断是否为空行，空行不用统计单词数
            if(!lineTemp.isEmpty()){
                String[] words = lineTemp.split("\\W+");
                //split分离生成的数组可能出现空字符串，所以要去除空的字符串，剩下的才为单词
                for(int i=0;i<words.length;i++){
                    if(!words[i].isEmpty()){
                        wordNum ++;
                    }
                }
            }
            else{
                blankLine++;
            }
            charNum+=lineTemp.replaceAll(" ","").length();
            fileMessage.append(line+"\n");
            matcher = commentLinePattern.matcher(lineTemp);
            if(matcher.matches()){commentLine++;}
            matcher =blankLinePattern.matcher(lineTemp);
            if(matcher.matches()){blankLine++;}
        }
        myFile.setWordNum(wordNum);
        myFile.setCharNum(charNum);
        myFile.setLineNum(lineNum);
        myFile.setBlankLineNum(blankLine);
        myFile.setCommentLineNum(commentLine);
        myFile.setCodeLineNum(lineNum-blankLine-commentLine);
        reader.close();
        myFile.setFileMessage(fileMessage.toString());
        return myFile;
    }

    public static List<Path> listSourceFiles(Path dir) {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry: stream) {
                //如果文件夹里面还有文件夹，内部文件夹循环调用此方法
                if(Files.isDirectory(entry)){
                    List<Path> innerDir = listSourceFiles(entry);
                    result.addAll(innerDir);
                }
                else {
                    result.add(entry);
                }
            }
        } catch (DirectoryIteratorException ex) {
           ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Path> getPathListForS(String fileName){
        String[] fileElements = fileName.split("\\\\");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<fileElements.length-1;i++){
            stringBuilder.append(fileElements[i]+"\\");
        }
        String parentDir = stringBuilder.toString();
        Path parentPath = FileSystems.getDefault().getPath(parentDir);
        String specialFileName = fileElements[fileElements.length-1].toString();
        specialFileName=specialFileName.replace(".","_").replace("?",".").replace("*",".+");
        Pattern pattern = Pattern.compile(specialFileName);
        List<Path> paths = listSourceFiles(parentPath);
        List<Path> newPaths = new ArrayList<Path>();
        for(Path p : paths){
            String name = p.getFileName().toString().replace(".","_");
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches()){
               newPaths.add(p);
            }
        }
        return newPaths;
    }

}
