package com.yanchuanl.tinydb.core;

public class MetaCommandHandler {
    public static void handle(String command) {
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
