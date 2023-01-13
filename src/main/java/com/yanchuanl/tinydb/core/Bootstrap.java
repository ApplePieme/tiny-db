package com.yanchuanl.tinydb.core;

import java.util.Scanner;

public class Bootstrap {
    private static final Scanner sc = new Scanner(System.in);
    
    private Bootstrap() {
        throw new UnsupportedOperationException("no instance");
    }
    
    public static void start() {
        while (true) {
            System.out.print("db > ");
            String command = sc.nextLine();
            
            if (command.startsWith(".")) {
                MetaCommandHandler.handle(command);
                continue;
            }

            Statement.execute(command);
        }
    }
}
