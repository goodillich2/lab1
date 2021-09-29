package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class CSVwritter {
    public static void write(String csv_file, String[][] str1) throws IOException {
        FileWriter csvWriter = new FileWriter(csv_file);
        /*csvWriter.append("Name");
        csvWriter.append(",");
        csvWriter.append("Role");
        csvWriter.append(",");
        csvWriter.append("Topic");
        csvWriter.append("\n");*/

        for(int i = 0; i<Main.rows; i++){
            for(int j = 0; j<str1[i].length;j++){
                String ins = Integer.toString(str1[i][j].length());
                csvWriter.append(ins) ;
                if(j == str1[i].length-1) break;
                else csvWriter.append(" + ") ;
            }
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
