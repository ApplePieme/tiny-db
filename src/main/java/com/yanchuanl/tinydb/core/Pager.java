package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class Pager {
    static File file;
    static FileInputStream input;
    static FileOutputStream output;
    static final Page[] pages = new Page[Constants.TABLE_MAX_PAGES];
    
    static void openFile(String filePath) {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            input = new FileInputStream(file);

            long filePageNumber = Pager.file.length() / Constants.PAGE_SIZE + 1;
            for (int i = 0; i < filePageNumber; i++) {
                pages[i] = new Page();
                pages[i].initRows();
            }

            System.out.println("use db file: " + filePath);
        } catch (Exception e) {
            System.out.println("unable to open file: " + filePath);
            System.exit(-1);
        }
    }
    
    static void flush() {
        try {
            output = new FileOutputStream(file);
            
            int pageNumber = Table.rowCount / Constants.ROWS_PER_PAGE + 1;
            for (int i = 0; i < pageNumber; i++) {
                if (pages[i] == null) {
                    continue;
                }

                pages[i].flush(i, pageNumber);
            }
        } catch (Exception e) {
            System.out.println("flush failed");
        }
    }
}
