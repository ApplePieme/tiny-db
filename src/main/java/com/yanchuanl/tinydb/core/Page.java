package com.yanchuanl.tinydb.core;

import java.nio.charset.StandardCharsets;

import static com.yanchuanl.tinydb.core.Statement.row;
import static com.yanchuanl.tinydb.common.Constants.*;

class Page {
    final byte[][] rows = new byte[PAGE_SIZE][ROW_SIZE];
    
    void serializeRow(int index) {
        rows[index] = new byte[ROW_SIZE];
        rows[index][ID_OFFSET] = (byte) (row.id >> 24 & 0xFF);
        rows[index][ID_OFFSET + 1] = (byte) (row.id >> 16 & 0xFF);
        rows[index][ID_OFFSET + 2] = (byte) (row.id >> 8 & 0xFF);
        rows[index][ID_OFFSET + 3] = (byte) (row.id & 0xFF);
        byte[] username = row.username.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(username, 0, rows[index], USERNAME_OFFSET, username.length);
        byte[] email = row.email.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(email, 0, rows[index], EMAIL_OFFSET, email.length);
    }
    
    void deserializeRow(int index) {
        row.id = ((rows[index][ID_OFFSET] & 0xFF) << 24) | ((rows[index][ID_OFFSET + 1] & 0xFF) << 16) | ((rows[index][ID_OFFSET + 2] & 0xFF) << 8) | (rows[index][ID_OFFSET + 3] & 0xFF);
        row.username = new String(rows[index], USERNAME_OFFSET, USERNAME_SIZE);
        row.email = new String(rows[index], EMAIL_OFFSET, EMAIL_SIZE);
    }
}
