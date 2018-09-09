package model;

/***
 * 一个对象，每次读取一个文件就生成一个此对象，用于存放文件的信息
 */
public class MyFile {

    private String fileName;

    private String fileMessage;

    private int charNum;

    private int wordNum;

    private int lineNum;

    private int codeLineNum;

    private int blankLineNum;

    private int commentLineNum;

    public String getFileName() {
        return fileName;
    }

    public String getFileMessage() {
        return fileMessage;
    }

    public void setFileMessage(String fileMessage) {
        this.fileMessage = fileMessage;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCharNum() {
        return charNum;
    }

    public void setCharNum(int charNum) {
        this.charNum = charNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getCodeLineNum() {
        return codeLineNum;
    }

    public void setCodeLineNum(int codeLineNum) {
        this.codeLineNum = codeLineNum;
    }

    public int getBlankLineNum() {
        return blankLineNum;
    }

    public void setBlankLineNum(int blankLineNum) {
        this.blankLineNum = blankLineNum;
    }

    public int getCommentLineNum() {
        return commentLineNum;
    }

    public void setCommentLineNum(int commentLineNum) {
        this.commentLineNum = commentLineNum;
    }

}
