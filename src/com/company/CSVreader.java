package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVreader {
    public static String delimiter = ",";
    static String[] tempArr = new String[count_number_of_rows()];
    private static int i;

    static {
        i = 0;
    }

    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                tempArr[i] = line;
                i++;
            }

        //System.out.println(Arrays.toString(tempArr));
            br.close();
        } catch (IOException ioe) {
            System.out.println("File not found");
        }
    }

    private static int count_number_of_rows(){
        try {
            int i = 0;
            File file = new File("test.csv");
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);

            String line = "";
            while ((line = b.readLine()) != null) {
                i++;
            }
            b.close();
            return i;
        } catch (IOException ioe) {
            System.out.println("File not found");
        }
        return 0;
    }
}