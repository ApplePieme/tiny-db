package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.Configs;
import com.yanchuanl.tinydb.common.ExecuteResult;

import java.util.Scanner;

public class Bootstrap {
    private static final Scanner sc = new Scanner(System.in);
    
    private Bootstrap() {
        throw new UnsupportedOperationException("no instance");
    }
    
    public static void start() {
        Pager.openFile(Configs.getDbFilename());
        
        while (true) {
            System.out.print("db > ");
            String command = sc.nextLine();
            
            if (command.startsWith(".")) {
                ExecuteResult result = MetaCommand.execute(command);
                switch (result) {
                    case META_COMMAND_SUCCESS:
                        break;
                    case META_COMMAND_UNRECOGNIZED_COMMAND:
                        System.out.printf("unrecognized command '%s'%n", command);
                        break;
                }
                continue;
            }

            if (command.length() < 6) {
                System.out.printf("unrecognized command '%s'%n", command);
                continue;
            }

            ExecuteResult result = Statement.execute(command);
            switch (result) {
                case EXECUTE_SUCCESS:
                    System.out.println("executed");
                    break;
                case UNRECOGNIZED_STATEMENT:
                    System.out.printf("unrecognized keyword at start of '%s'%n", command);
                    break;
                case SYNTAX_ERROR:
                    System.out.println("syntax error, could not parse statement");
                    break;
                case TABLE_FULL:
                    System.out.println("error: table full");
                    break;
                case STRING_TOO_LONG:
                    System.out.println("string is too long");
                    break;
                case INVALID_ID:
                    System.out.println("id must be a positive integer");
                    break;
            }
        }
    }
}
