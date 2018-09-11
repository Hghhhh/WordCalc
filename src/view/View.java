package view;

import model.MyFile;
import util.FileUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class View extends JFrame implements ActionListener{
    private static final String newline = "\n";
    private  JButton openButton;
    private JPanel jPanel ;
    private JScrollPane textScrollPane;
    private  JTextArea text;

    public View(){
        super("WordCalc");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400, 300);
        this.setVisible(true);
        this.init();
        this.setPreferredSize(new Dimension(1200,300));
        this.pack();
    }

    private void init(){
        openButton = new JButton("Please Choose File");
        openButton.setFont(new Font(null,Font.BOLD,25));
        openButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        jPanel = new JPanel(new BorderLayout());
        jPanel.setVisible(true);
        text = new JTextArea(50,10);
        text.setMargin(new Insets(5,5,5,5));
        text.setEditable(false);
        text.setFont(new Font(null,Font.BOLD,20));
        textScrollPane = new JScrollPane(text);
        jPanel.add(buttonPanel, BorderLayout.PAGE_START);
        jPanel.add(textScrollPane, BorderLayout.CENTER);
        this.add(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Handle open button action.
        if (e.getSource() == openButton) {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                try {
                   List<MyFile> fileList = FileUtil.readFile(file.getAbsolutePath().replaceAll("\\\\","\\\\\\\\"));
                   MyFile f = fileList.get(0);
                   text.append(f.getFileName()+" Message:\n");
                   text.append("CharNumber: " + f.getCharNum());
                   text.append("  WordNumber: " + f.getWordNum());
                   text.append("  LineNumber: " + f.getLineNum());
                   text.append("  BlankLineNumber: " + f.getBlankLineNum());
                   text.append("  CommentLineNumber: " + f.getCommentLineNum());
                   text.append("  CodeLineNumber: "+ f.getCodeLineNum()+newline);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                text.append("Open command cancelled by user." + newline);
            }
        }

    }

}
