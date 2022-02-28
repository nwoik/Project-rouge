package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadCSVFile {
    private String filepath;
    public LinkedList<String[]> rows = new LinkedList<>();

    public ReadCSVFile(String filepath) throws IOException {
        this.filepath = filepath;
        BufferedReader file = new BufferedReader(new FileReader(this.filepath));
        this.loadStrings(file);
    }

    private void loadStrings(BufferedReader file) throws IOException {
        for (String row : file.lines().toList()) {
            String[] tempRow = row.strip().split(",");
            this.rows.add(tempRow);
        }
    }
}
