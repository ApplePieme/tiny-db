package com.yanchuanl.tinydb.common;

public class Constants {
    public static final int ID_SIZE = 4;
    public static final int USERNAME_SIZE = 32;
    public static final int EMAIL_SIZE = 255;
    public static final int ID_OFFSET = 0;
    public static final int USERNAME_OFFSET = ID_OFFSET + ID_SIZE;
    public static final int EMAIL_OFFSET = USERNAME_OFFSET + USERNAME_SIZE;
    public static final int ROW_SIZE = ID_SIZE + USERNAME_SIZE + EMAIL_SIZE;
    
    public static final int PAGE_SIZE = 4096;
    public static final int TABLE_MAX_PAGES = 100;
    public static final int ROWS_PER_PAGE = PAGE_SIZE / ROW_SIZE;
    public static final int TABLE_MAX_ROWS = ROWS_PER_PAGE * TABLE_MAX_PAGES;
}
