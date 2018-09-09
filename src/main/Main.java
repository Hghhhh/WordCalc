package main;

import util.WordCalc;
import view.View;

public class Main {

    public static void main(String[] args){
        if(args[0].equals("-x")){
            new View();
        }
        else {
            WordCalc wordCalc = new WordCalc(args);
            wordCalc.run();
        }
    }
}
