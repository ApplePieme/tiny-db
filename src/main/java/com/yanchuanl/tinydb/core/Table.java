package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Constants;

class Table {
    static int rowCount;
    
    static Page createPageIfAbsent(int rowNumber) {
        int pageNumber = rowNumber / Constants.ROWS_PER_PAGE;
        if (pageNumber > Constants.TABLE_MAX_PAGES) {
            System.out.printf("tried to fetch page number out of bounds: %d, max page number: %d%n", pageNumber, Constants.TABLE_MAX_PAGES);
            System.exit(-1);
        }
        
        Page page = Pager.pages[pageNumber];
        if (page == null) {
            page = new Page();
            Pager.pages[pageNumber] = page;
        }
        return page;
    }
}
