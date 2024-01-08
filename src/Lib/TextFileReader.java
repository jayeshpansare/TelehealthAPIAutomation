package Lib;

import org.junit.Test;

import java.io.*;

public class TextFileReader {
    static String projectPath = System.getProperty("user.dir");
    @Test
    public void readFile() throws IOException {
        FileReader objFileRed = new FileReader(projectPath+"/src/Data/testData.txt");
        BufferedReader st = new BufferedReader(objFileRed);
        String st1;
        while ((st1=st.readLine())!=null){
            System.out.println(st1);
        }
        System.out.println();
    }
    @Test
    public void writeFile() throws IOException {
        FileWriter objwrite = new FileWriter(projectPath+"/src/Data/testData.txt");
        objwrite.write("Now we write some thing");
        objwrite.flush();
        objwrite.close();
    }
}
