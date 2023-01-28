package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Constants;
import com.yanchuanl.tinydb.common.ExecuteResult;

public class Statement {
    static final Row row = new Row();
    
    public static ExecuteResult execute(String command) {
        switch (command.substring(0, 6)) {
            case "insert":
                return executeInsert(command.substring(6));
            case "select":
                return executeSelect();
            default:
                return ExecuteResult.UNRECOGNIZED_STATEMENT;
        }
    }
    
    private static ExecuteResult executeInsert(String command) {
        if (Table.rowCount >= Constants.TABLE_MAX_ROWS) {
            return ExecuteResult.TABLE_FULL;
        }
        
        String[] columns = command.trim().split("\\s+");
        if (columns.length != 3) {
            return ExecuteResult.SYNTAX_ERROR;
        }
        
        try {
            row.id = Integer.parseInt(columns[0]);
            if (row.id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return ExecuteResult.INVALID_ID;
        }
        row.username = columns[1];
        row.email = columns[2];
        if (row.username.length() > Constants.USERNAME_SIZE || row.email.length() > Constants.EMAIL_SIZE) {
            return ExecuteResult.STRING_TOO_LONG;
        }
        
        Table.createPageIfAbsent(Table.rowCount).serializeRow(Table.rowCount % Constants.ROWS_PER_PAGE);
        ++Table.rowCount;

        return ExecuteResult.EXECUTE_SUCCESS;
    }
    
    private static ExecuteResult executeSelect() {
        for (int i = 0; i < Table.rowCount; i++) {
            Table.createPageIfAbsent(i).deserializeRow(i % Constants.ROWS_PER_PAGE);
            System.out.printf("[%d, %s, %s]\n", row.id, row.username, row.email);
        }
        
        return ExecuteResult.EXECUTE_SUCCESS;
    }
}
