package com.xx.jersey.demo.oauth;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;

/**
 * Created by hzxiongxin on 2016/11/18.
 */
public class SimpleOAuthService {

    private static String accessToken = null;//访问令牌

    private static OAuth2CodeGrantFlow flow;//授权码流

    private static ClientIdentifier clientIdentifier;//客户端凭证

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        SimpleOAuthService.accessToken = accessToken;
    }

    public static OAuth2CodeGrantFlow getFlow() {
        return flow;
    }

    public static void setFlow(OAuth2CodeGrantFlow flow) {
        SimpleOAuthService.flow = flow;
    }

    public static ClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    public static void setClientIdentifier(ClientIdentifier clientIdentifier) {
        SimpleOAuthService.clientIdentifier = clientIdentifier;
    }
}
