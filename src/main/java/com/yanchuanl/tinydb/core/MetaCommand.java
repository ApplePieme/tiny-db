package com.yanchuanl.tinydb.core;

public class MetaCommand {
    public static void execute(String command) {
        switch (command) {
            case ".exit":
                System.exit(0);
                break;
            default:
                System.out.printf("unrecognized command '%s'%n", command);
                break;
        }
    }
}
