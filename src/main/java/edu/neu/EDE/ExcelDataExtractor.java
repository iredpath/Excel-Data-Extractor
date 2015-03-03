package edu.neu.EDE;

import edu.neu.EDE.io.WorkbookReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelDataExtractor {

    private WorkbookReader reader;
    private ArrayList<String> invalidFiles;

    public ExcelDataExtractor() {
        this.reader = new WorkbookReader();
        this.invalidFiles = new ArrayList<String>();
    }

    public static void main(String[] args) throws IOException {
        ExcelDataExtractor extractor = new ExcelDataExtractor();
        for (String s: args) {
            File f = new File(s);
            extractor.readFile(f);
        }
    }

    private void readFile(File f) throws IOException {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File subFile: files) {
                readFile(subFile);
            }
        } else if (f.getName().contains(".xlsx") && !f.getName().contains("~$")) {
            reader.readFile(f);
        } else {
            invalidFiles.add(f.getAbsolutePath());
        }
    }
}