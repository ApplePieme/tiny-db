package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Constants;

class Table {
    static int rowCount;
    static final Page[] pages = new Page[Constants.TABLE_MAX_PAGES];
    
    static Page createIfAbsent(int rowNumber) {
        Page page = pages[rowNumber / Constants.ROWS_PER_PAGE];
        if (page == null) {
            page = new Page();
            pages[rowNumber / Constants.ROWS_PER_PAGE] = page;
        }
        return page;
    }
}
