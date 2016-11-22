package com.xx.jersey.demo.http;

import javax.ws.rs.HeaderParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@XmlRootElement
public class HeaderBean {

    @HeaderParam("host")
    private String host;

    @HeaderParam("referer")
    private String referer;

    @HeaderParam("user-agent")
    private String userAgent;

    @HeaderParam("connection")
    private String connection;

    @HeaderParam("accept-charset")
    private String acceptCharset;

    @HeaderParam("accept-encoding")
    private String acceptEncoding;

    @HeaderParam("accept-language")
    private String acceptLanguage;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getAcceptCharset() {
        return acceptCharset;
    }

    public void setAcceptCharset(String acceptCharset) {
        this.acceptCharset = acceptCharset;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }
}
