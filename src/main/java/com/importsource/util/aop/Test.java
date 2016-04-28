package com.importsource.util.aop;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 13-9-24
 * Time: 上午9:54
 */
public class Test {
    public static Logger logger = Logger.getLogger(Test.class.getSimpleName());
    public static void main(String[] args) {

        BussinessService bs = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
        bs.login("zhangsan", "123456");
        bs.find();

        logger.info("====================================================================================");

        WorkService ws = LogInvoHandler.getProxyInstance(WorkServiceImpl.class);
        ws.work();
        ws.sleep();

        logger.info("=====================================================================================");

        BussinessService bss = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
        bss.login("lisi", "654321");
        bss.find();

    }
}