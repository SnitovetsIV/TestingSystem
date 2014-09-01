package by.epam.testingsystem.listener;

import by.epam.testingsystem.dao.pool.ConnectionPool;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String logConfigPath = context.getRealPath(context.getInitParameter(Constants.CONTEXT_PARAM_NAME_LOG_CONFIG_PATH));
        new DOMConfigurator().doConfigure(logConfigPath, LogManager.getLoggerRepository());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.releasePool();
    }

}
