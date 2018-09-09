package util;

import model.MyFile;

import java.io.IOException;
import java.util.List;
import java.nio.file.Path;
public class WordCalc {

    private static String[] args;

    public WordCalc(String[] args) {
        this.args  = args;
    }

    public WordCalc() {
    }

    /**
     * 根据第一个参数判断调用的方法
     */
    public void run(){
        try{
            switch(args[0]){
                case "-c":
                    charCount(args[1]);
                    break;
                case "-w":
                    wordCount(args[1]);
                    break;
                case "-l":
                    lineCount(args[1]);
                    break;
                case "-s":
                    countForSpecialFileName();
                    break;
                case "-a":
                    moreMessageCount(args[1]);
                    break;
                default:
                    error();
            }
        } catch (Exception e){
            error();
            e.printStackTrace();
        }
    }

    private String charCount(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<MyFile> myFiles = FileUtil.readFile(path);
        for(MyFile myFile : myFiles){
            String putOut = myFile.getFileName()+" charNumber : "+myFile.getCharNum();
            System.out.println(putOut);
            stringBuilder.append(putOut+"\n");
        }
        return stringBuilder.toString();
    }

    private String wordCount(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<MyFile> myFiles = FileUtil.readFile(path);
        for(MyFile myFile : myFiles){
            String putOut = myFile.getFileName()+" wordNumber : "+myFile.getWordNum();
            System.out.println(putOut);
            stringBuilder.append(putOut+"\n");
        }
        return stringBuilder.toString();
    }

    private String lineCount(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<MyFile> myFiles = FileUtil.readFile(path);
        for(MyFile myFile : myFiles){
            String putOut = myFile.getFileName()+" lineNumber : "+myFile.getLineNum();
            System.out.println(putOut);
            stringBuilder.append(putOut+"\n");
        }
        return stringBuilder.toString();
    }

    private void countForSpecialFileName() throws IOException {
        List<Path> paths = FileUtil.getPathListForS(args[2]);
        for(Path path : paths){
            switch (args[1]){
                case "-c":
                    charCount(path.toString());
                    break;
                case "-w":
                    wordCount(path.toString());
                    break;
                case "-l":
                    lineCount(path.toString());
                    break;
                case "-a":
                    moreMessageCount(path.toString());
                    break;
                default:
                    error();
            }
        }
    }

    private String moreMessageCount(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<MyFile> myFiles = FileUtil.readFile(path);
        for(MyFile myFile : myFiles){
            String putOutCodeLineNum = myFile.getFileName()+" CodeLineNumber : "+myFile.getCodeLineNum();
            String putOutBlankLineNum = "\tBlankLineNumber : "+myFile.getBlankLineNum();
            String putOutCommentLineNum = "\tCommentLineNumber : "+myFile.getCommentLineNum();
            System.out.println(putOutCodeLineNum+putOutBlankLineNum+putOutCommentLineNum);
            stringBuilder.append(putOutCodeLineNum+putOutBlankLineNum+putOutCommentLineNum+"\n");
        }
        return stringBuilder.toString();
    }

    private void error() {
        System.out.println("您输入的参数有误！");
    }


    public String setTextForView(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charCount(path)+"\n");
        stringBuilder.append(wordCount(path)+"\n");
        stringBuilder.append(lineCount(path)+"\n");
        stringBuilder.append(moreMessageCount(path+"\n"));
        return stringBuilder.toString();

    }


}
