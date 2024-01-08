package Lib;

import org.junit.Test;
import org.mozilla.javascript.ast.WhileLoop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReadFile {
    static String projectPath = System.getProperty("user.dir");
    @Test
    public void readCSVFile() throws IOException {
        FileReader objReader = new FileReader(projectPath+"/src/Data/currency.csv");
        BufferedReader st = new BufferedReader(objReader);
        String st1;
        while((st1=st.readLine())!=null){
            String[] currency = st1.split(",");
            System.out.println("Code="+currency[0]+" Symbol="+currency[1]+" Country="+currency[2]);
        }
    }
}
