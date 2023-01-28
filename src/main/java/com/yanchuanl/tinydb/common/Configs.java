package com.yanchuanl.tinydb.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.io.IOException;

public class Configs {
    private static String dbFilename;
    
    static {
        try {
            init(new File("conf/tiny-db.conf"));
        } catch (Exception e) {
            System.out.println("load config file failed");
            System.exit(-1);
        }
    }
    
    static void init(File configFile) throws Exception {
        if (!configFile.exists() || !configFile.isFile()) {
            throw new IOException();
        }
        System.out.println("load config file: " + configFile.getCanonicalPath());

        Config config = ConfigFactory.parseFile(configFile);
        
        if (!config.hasPath("db-filename")) {
            System.out.println("must supply a db filename");
            System.exit(-1);
        }
        dbFilename = config.getString("db-filename");
    }
    
    public static String getDbFilename() {
        return dbFilename;
    }
}
