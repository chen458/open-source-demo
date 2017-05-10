package com.chen.demo.thrift.proxy;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Constructor;

/**
 * client 代理类
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/10
 * @time 下午11:53
 */
public class ThriftClientProxy<T> implements FactoryBean<Object>, ApplicationContextAware, InitializingBean {
    private String host;
    private int port;
    private T serviceClient;

    public ThriftClientProxy() {
    }

    public ThriftClientProxy(String host, int port, T serviceClient) {
        this.host = host;
        this.port = port;
        this.serviceClient = serviceClient;
    }

    public T getClientInstance() throws Exception {
        TTransport transport = new TSocket(host, port);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        Constructor<?> constructor = serviceClient.getClass().getConstructor(protocol.getClass());
        return  (T)constructor.newInstance(protocol);
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public T getServiceClient() {
        return serviceClient;
    }

    public void setServiceClient(T serviceClient) {
        this.serviceClient = serviceClient;
    }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
