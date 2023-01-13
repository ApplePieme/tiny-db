package com.yanchuanl.tinydb.core;

import static com.yanchuanl.tinydb.common.Constants.*;

class Table {
    static int rowCount;
    static final Page[] pages = new Page[TABLE_MAX_PAGES];
    
    static Page getPage(int rowNumber) {
        Page page = pages[rowNumber / ROWS_PER_PAGE];
        if (page == null) {
            page = new Page();
            pages[rowNumber / ROWS_PER_PAGE] = page;
        }
        return page;
    }
}
