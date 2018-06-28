package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.IExchangeService;
import service.impl.ItemServiceImpl;
import service.impl.UserServiceImpl;


//@ContextConfiguration(locations = { "classpath:/config/applicationContext.xml",
//		"classpath:/config/springConfig.xml" })
@SuppressWarnings("unused")
public class test {
    private static final Log logger = LogFactory
            .getLog(test.class);

    @Autowired
    private ItemServiceImpl itemDaoImpl;
    @Autowired
    private UserServiceImpl userDaoImpl;

    @Autowired
    IExchangeService excDao;

    @Test
    public void test() {
        StringBuffer a = new StringBuffer();
        for (int i=0;i<100000;i++){
            a=a.append("123");
        }

    }

    @Test
    public void test2() {
        StringBuilder a = new StringBuilder();
        for (int i=0;i<5000000;i++){
            a=a.append("123");
        }

    }
    @Test
    public void test3() {
        String a = new String();
        for (int i=0;i<500000;i++){
            a=a+"123";
        }

    }

}
