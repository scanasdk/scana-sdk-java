/*
 * @(#) HttpClientConfig.java 2020-07-08
 *
 * Copyright 2020 NetEase.com, Inc. All rights reserved.
 */

package com.newkms.qixincha.http;

/**
 * HTTP客户端相关配置
 */
public class HttpClientConfig {

    public static final ProtocolEnum DEFAULT_PROTOCOL = ProtocolEnum.HTTPS;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 5000;
    public static final long DEFAULT_MAX_IDLE_TIME_MILLIS = 60 * 1000L;
    public static final long DEFAULT_CONNECTION_KEEP_ALIVE_MILLIS = 2 * 60 * 1000L;
    public static final long DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS = 1000L;
    public static final long DEFAULT_CONNECTION_TIMEOUT_MILLIS = 15000L;
    public static final long DEFAULT_RESPONSE_TIMEOUT_MILLIS = 20000L;
    public static final int DEFAULT_MAX_CONNECTION_COUNT = 200;
    public static final int DEFAULT_MAX_CONNECTION_COUNT_PER_ROUTE = 20;

    private ProtocolEnum protocol = DEFAULT_PROTOCOL;
    private int socketTimeoutMillis = DEFAULT_SOCKET_TIMEOUT_MILLIS;
    private long maxIdleTimeMillis = DEFAULT_MAX_IDLE_TIME_MILLIS;
    private long connectionKeepAliveMillis = DEFAULT_CONNECTION_KEEP_ALIVE_MILLIS;
    private long connectionRequestTimeoutMillis = DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS;
    private long connectionTimeoutMillis = DEFAULT_CONNECTION_TIMEOUT_MILLIS;
    private long responseTimeoutMillis = DEFAULT_RESPONSE_TIMEOUT_MILLIS;
    private int maxConnectionCount = DEFAULT_MAX_CONNECTION_COUNT;
    private int maxConnectionCountPerRoute = DEFAULT_MAX_CONNECTION_COUNT_PER_ROUTE;

    public ProtocolEnum getProtocol() {
        return protocol;
    }

    public ProtocolEnum protocol() {
        return protocol;
    }

    public void setProtocol(ProtocolEnum protocol) {
        this.protocol = protocol;
    }


    /**
     * 协议。默认值：{@link #DEFAULT_PROTOCOL}
     */
    public HttpClientConfig protocol(ProtocolEnum protocol) {
        this.protocol = protocol;
        return this;
    }

    public int getSocketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    public void setSocketTimeoutMillis(int socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    public int socketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    /**
     * 默认的 socket I/O 阻塞超时时间。单位：毫秒。默认值：{@link #DEFAULT_SOCKET_TIMEOUT_MILLIS}
     */
    public HttpClientConfig socketTimeoutMillis(int socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
        return this;
    }

    public long getMaxIdleTimeMillis() {
        return maxIdleTimeMillis;
    }

    public void setMaxIdleTimeMillis(long maxIdleTimeMillis) {
        this.maxIdleTimeMillis = maxIdleTimeMillis;
    }

    public long maxIdleTimeMillis() {
        return maxIdleTimeMillis;
    }

    /**
     * 连接的最长空闲时间。单位：毫秒。默认值：{@link #DEFAULT_MAX_IDLE_TIME_MILLIS}
     */
    public HttpClientConfig maxIdleTimeMillis(long maxIdleTimeMillis) {
        this.maxIdleTimeMillis = maxIdleTimeMillis;
        return this;
    }

    public long getConnectionRequestTimeoutMillis() {
        return connectionRequestTimeoutMillis;
    }

    public void setConnectionRequestTimeoutMillis(long connectionRequestTimeout) {
        this.connectionRequestTimeoutMillis = connectionRequestTimeout;
    }

    public long connectionRequestTimeoutMillis() {
        return connectionRequestTimeoutMillis;
    }

    /**
     * 从连接池获取连接的超时时间。0表示无超时限制。单位：毫秒。默认值：{@link #DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS}
     */
    public HttpClientConfig connectionRequestTimeoutMillis(long connectionRequestTimeoutMillis) {
        this.connectionRequestTimeoutMillis = connectionRequestTimeoutMillis;
        return this;
    }

    public long getConnectionKeepAliveMillis() {
        return connectionKeepAliveMillis;
    }

    public void setConnectionKeepAliveMillis(long connectionKeepAliveMillis) {
        this.connectionKeepAliveMillis = connectionKeepAliveMillis;
    }

    public long connectionKeepAliveMillis() {
        return connectionKeepAliveMillis;
    }

    /**
     * 默认的连接保持时间。单位：毫秒。默认值：{@link #DEFAULT_CONNECTION_KEEP_ALIVE_MILLIS}
     */
    public HttpClientConfig connectionKeepAliveMillis(long connectionKeepAliveMillis) {
        this.connectionKeepAliveMillis = connectionKeepAliveMillis;
        return this;
    }

    public long getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public void setConnectionTimeoutMillis(long connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
    }

    public long connectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    /**
     * 建立一个新连接的超时时间。单位：毫秒。默认值：{@link #DEFAULT_CONNECTION_TIMEOUT_MILLIS}
     */
    public HttpClientConfig connectionTimeoutMillis(long connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
        return this;
    }

    public long getResponseTimeoutMillis() {
        return responseTimeoutMillis;
    }

    public void setResponseTimeoutMillis(long responseTimeoutMillis) {
        this.responseTimeoutMillis = responseTimeoutMillis;
    }

    public long responseTimeoutMillis() {
        return responseTimeoutMillis;
    }

    /**
     * 等待服务器返回响应的超时时间。单位：毫秒。默认值：20秒
     */
    public HttpClientConfig responseTimeoutMillis(long responseTimeoutMillis) {
        this.responseTimeoutMillis = responseTimeoutMillis;
        return this;
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public void setMaxConnectionCount(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
    }

    public int maxConnectionCount() {
        return maxConnectionCount;
    }

    /**
     * 同一时刻最大连接数。默认值：{@link #DEFAULT_MAX_CONNECTION_COUNT}
     */
    public HttpClientConfig maxConnectionCount(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
        return this;
    }

    public int getMaxConnectionCountPerRoute() {
        return maxConnectionCountPerRoute;
    }

    public void setMaxConnectionCountPerRoute(int maxConnectionCountPerRoute) {
        this.maxConnectionCountPerRoute = maxConnectionCountPerRoute;
    }

    public int maxConnectionCountPerRoute() {
        return maxConnectionCountPerRoute;
    }

    /**
     * 针对每个域名的同一时刻最大连接数默认值。默认值：{@link #DEFAULT_MAX_CONNECTION_COUNT_PER_ROUTE}
     */
    public HttpClientConfig maxConnectionCountPerRoute(int maxConnectionCountPerRoute) {
        this.maxConnectionCountPerRoute = maxConnectionCountPerRoute;
        return this;
    }

    @Override
    public String toString() {
        return "HttpClientConfig(" +
                "protocol=" + protocol +
                ", socketTimeoutMillis=" + socketTimeoutMillis +
                ", maxIdleTimeMillis=" + maxIdleTimeMillis +
                ", connectionRequestTimeoutMillis=" + connectionRequestTimeoutMillis +
                ", connectionKeepAliveMillis=" + connectionKeepAliveMillis +
                ", connectionTimeoutMillis=" + connectionTimeoutMillis +
                ", responseTimeoutMillis=" + responseTimeoutMillis +
                ", maxConnectionCount=" + maxConnectionCount +
                ", maxConnectionCountPerRoute=" + maxConnectionCountPerRoute +
                ")";
    }

    public static HttpClientConfig defaultConfig() {
        return new HttpClientConfig();
    }
}
