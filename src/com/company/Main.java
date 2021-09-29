package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int rows;
    public static void main(String[] args) throws IOException {
        Scanner p = new Scanner(System.in);
        System.out.print("Enter the name of csv file: ");
        String csvFile = p.nextLine();
        System.out.print("Enter the name of csv file: ");
        String result_csv = p.nextLine();
        System.out.print("Enter delimiter: ");
        CSVreader.delimiter = p.nextLine();
        CSVreader.read(csvFile);


        rows = CSVreader.tempArr.length;

        String[][] str1 = new String[rows][10];
        for(int i =0; i<rows; i++){
            if(CSVreader.delimiter.equals(","))
                str1[i] = CSVreader.tempArr[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            else if (CSVreader.delimiter.equals(";"))
                str1[i] = CSVreader.tempArr[i].split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            else  str1[i] = CSVreader.tempArr[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        }

        for(int i = 0; i<rows; i++){
            for(int j = 0; j<str1[i].length;j++){
                str1[i][j] = fix_word(str1[i][j]);
            }
        }

        for(int i = 0; i<rows;i++)
            System.out.println(Arrays.toString(str1[i]));

        CSVwritter.write(result_csv, str1);

    }

    public static String fix_word(String string) {
        int number = 0;//number og quotes
        char[] a = string.toCharArray();// massive of chars

        int place = 0; //for case 2/3/4

        int buf = 0; //for case 4

        // to count number of quotes
        for(char i:a){
            if(i == '\"') number++;
        }

        //case 1
        if((a[0] == '\"' && a[a.length-1] == '\"' && number == 2) )
        {
            char[] b = new char[a.length-2]; // for case 1
            for(int i = 0; i<b.length; i++){
                b[i]  = a[i+1];
            }
            return new String(b);
        }

        //case 2
        else if((a[0] != '\"' && a[a.length-1] != '\"' && number > 1) )
        {
            char[] l = new char[a.length-1];//for case 2
            for(int i = 0; i<a.length; i++)
            {
               if(a[i] == '\"' && a[i+1]== '\"')
               {
                   place = i;
                }
            }
            if(place != 0)
            {
                for(int i = 0; i<l.length; i++)
                {
                    if(i>place) l[i]  = a[i+1];
                    else l[i]  = a[i];
                }
                return new String(l);
            }

        }

        //case 3
        else if((a[0] == '\"' && a[a.length-1] == '\"' && number > 3) && check_repeats(a) )
        {
            char[] t = new char[a.length-3];
            for(int i = 0; i<a.length; i++)
            {
                if(a[i] == '\"' && a[i+1]== '\"')
                {
                    place = i;
                }

                if(place != 0)
                {
                    for(int h = 0; h<t.length; h++)
                    {
                        if  (h>place-1) t[h]  = a[h+2];
                        else t[h]  = a[h+1];
                    }
                    return new String(t);
                }
            }
        }

        //case 4
        else if(a[0] == '\"' && a[a.length-1] == '\"' && number > 3 && !check_repeats(a)) {
            char[] u = new char[a.length - 2];
                for (int r = 0; r < u.length; r++)
                {
                    if(a[r] == '\"') buf++;
                    if  (buf > 1 ) u[r] = a[r + 2];
                    else if (buf<2)  u[r] = a[r + 1];
                }
                u[3] =' ';
                return new String(u);

        }

        else return string;

        return string;
    }


public static boolean check_repeats(char[] chars) {
    for (int i = 0; i < chars.length; i++) {
        if (i!=chars.length-1 && chars[i] == '\"' && chars[i + 1] == '\"') return true;
    }
    return false;
}

}