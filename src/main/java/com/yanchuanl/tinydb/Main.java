package com.yanchuanl.tinydb;

import com.yanchuanl.tinydb.core.Bootstrap;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Main {
    static {
        InitialLogger("log4j.properties", "conf/log4j.properties");
    }

    private static void InitialLogger(String... paths) {
        for (String path : paths) {
            if (new File(path).isFile()) {
                PropertyConfigurator.configureAndWatch(path, 10000);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        Bootstrap.start();
    }
}
