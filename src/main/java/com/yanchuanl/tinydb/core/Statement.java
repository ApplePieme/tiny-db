package com.yanchuanl.tinydb.core;

import static com.yanchuanl.tinydb.common.Constants.*;

public class Statement {
    static final Row row = new Row();
    
    public static void execute(String command) {
        switch (command.substring(0, 6)) {
            case "insert":
                executeInsert(command.substring(6));
                break;
            case "select":
                executeSelect();
                break;
            default:
                System.out.printf("unrecognized keyword at start of '%s'%n", command);
                break;
        }
    }
    
    private static void executeInsert(String command) {
        if (Table.rowCount >= TABLE_MAX_ROWS) {
            System.out.println("error: table full");
            return;
        }
        
        String[] columns = command.trim().split("\\s+");
        if (columns.length != 3) {
            System.out.println("syntax error, could not parse statement");
            return;
        }
        
        row.id = Integer.parseInt(columns[0]);
        row.username = columns[1];
        row.email = columns[2];
        
        Table.getPage(Table.rowCount).serializeRow(Table.rowCount % PAGE_SIZE);
        ++Table.rowCount;

        System.out.println("executed");
    }
    
    private static void executeSelect() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 63; i++) {
            builder.append("-");
        }
        builder.append("\n- id");
        for (int i = 0; i < 20; i++) {
            builder.append(" ");
        }
        
        
        for (int i = 0; i < Table.rowCount; i++) {
            Table.getPage(i).deserializeRow(i % PAGE_SIZE);
            System.out.printf("[%d, %s, %s]\n", row.id, row.username, row.email);
        }
    }
}
