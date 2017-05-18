package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by Vaisiliy Es'kin on 05/17/17.
 */
public class SecurityProxyConnector implements Connector {
    SimpleConnector simpleConnector;
    SecurityChecker securityChecker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String s) {
        this.simpleConnector = new SimpleConnector(s);
    }

    @Override
    public void connect() {
        if(securityChecker.performSecurityCheck())
        {
            simpleConnector.connect();
        }
    }
}
