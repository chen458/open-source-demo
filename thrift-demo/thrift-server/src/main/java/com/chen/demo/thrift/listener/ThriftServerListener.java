package com.chen.demo.thrift.listener;

import com.chen.demo.thrift.proxy.ThriftServerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/11
 * @time 上午12:51
 */
public class ThriftServerListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(ServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

            List<ThriftServerProxy> list = ((List<ThriftServerProxy>) context.getBean("thriftServerList"));
            if (list != null && list.size() > 0) {
                list.forEach(ThriftServerProxy::start);
            }
            logger.info("Thrift Server监听器启动成功!");
        } catch (Exception e) {
            logger.error("Thrift Server监听器启动错误", e);
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
