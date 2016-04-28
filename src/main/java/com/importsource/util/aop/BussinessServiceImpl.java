package com.importsource.util.aop;

import java.util.logging.Logger;

public class BussinessServiceImpl implements BussinessService {
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public String login(String username, String password) {
        return "login success";
    }

    public String find() {
        return "find success";
    }

}