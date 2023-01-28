package com.yanchuanl.tinydb.core;

class Cursor {
    int index;
    boolean endOfTable;
    
    static Cursor createCursorWithStart() {
        Cursor cursor = new Cursor();
        cursor.index = 0;
        cursor.endOfTable = Table.rowCount == 0;
        return cursor;
    }
    
    static Cursor createCursorWithEnd() {
        Cursor cursor = new Cursor();
        cursor.index = Table.rowCount;
        cursor.endOfTable = true;
        return cursor;
    }
    
    void advance() {
        ++index;
        if (index >= Table.rowCount) {
            endOfTable = true;
        }
    }
}
