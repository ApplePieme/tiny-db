package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Constants;

import java.nio.charset.StandardCharsets;

class Page {
    final byte[][] rows = new byte[Constants.ROWS_PER_PAGE][Constants.ROW_SIZE];
    
    void serializeRow(int index) {
        rows[index] = new byte[Constants.ROW_SIZE];
        rows[index][Constants.ID_OFFSET] = (byte) (Statement.row.id >> 24 & 0xFF);
        rows[index][Constants.ID_OFFSET + 1] = (byte) (Statement.row.id >> 16 & 0xFF);
        rows[index][Constants.ID_OFFSET + 2] = (byte) (Statement.row.id >> 8 & 0xFF);
        rows[index][Constants.ID_OFFSET + 3] = (byte) (Statement.row.id & 0xFF);
        byte[] username = Statement.row.username.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(username, 0, rows[index], Constants.USERNAME_OFFSET, username.length);
        byte[] email = Statement.row.email.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(email, 0, rows[index], Constants.EMAIL_OFFSET, email.length);
    }
    
    void deserializeRow(int index) {
        Statement.row.id = ((rows[index][Constants.ID_OFFSET] & 0xFF) << 24) | ((rows[index][Constants.ID_OFFSET + 1] & 0xFF) << 16) | ((rows[index][Constants.ID_OFFSET + 2] & 0xFF) << 8) | (rows[index][Constants.ID_OFFSET + 3] & 0xFF);
        Statement.row.username = new String(rows[index], Constants.USERNAME_OFFSET, Constants.USERNAME_SIZE);
        Statement.row.email = new String(rows[index], Constants.EMAIL_OFFSET, Constants.EMAIL_SIZE);
    }
    
    void initRows() {
        try {
            byte[] page = new byte[Constants.PAGE_SIZE];
            int bytesRead = Pager.input.read(page, 0, Constants.ROW_SIZE * Constants.ROWS_PER_PAGE);
            int rowsIndex = 0;
            int pageIndex = 0;
            while (pageIndex < bytesRead && rowsIndex < Constants.ROWS_PER_PAGE) {
                rows[rowsIndex] = new byte[Constants.ROW_SIZE];
                System.arraycopy(page, pageIndex, rows[rowsIndex], 0, Constants.ROW_SIZE);
                ++rowsIndex;
                pageIndex += Constants.ROW_SIZE;
                ++Table.rowCount;
            }
        } catch (Exception e) {
            System.out.println("error reading file");
        }
    }
    
    void flush(int currPage, int pageNumber) throws Exception {
        int rowNumber = Constants.ROWS_PER_PAGE;
        if (currPage == pageNumber - 1) {
            rowNumber = Table.rowCount % Constants.ROWS_PER_PAGE;
        }
        for (int i = 0; i < rowNumber; i++) {
            Pager.output.write(rows[i], 0, rows[i].length);
        }
        Pager.output.flush();
    }
}
