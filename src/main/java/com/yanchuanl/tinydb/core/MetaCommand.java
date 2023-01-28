package com.yanchuanl.tinydb.core;

import com.yanchuanl.tinydb.common.ExecuteResult;

public class MetaCommand {
    public static ExecuteResult execute(String command) {
        switch (command) {
            case ".exit":
                System.exit(0);
                return ExecuteResult.META_COMMAND_SUCCESS;
            default:
                return ExecuteResult.META_COMMAND_UNRECOGNIZED_COMMAND;
        }
    }
}
