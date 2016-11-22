package com.xx.jersey.demo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Compress
public class CompressionWriterInterceptor implements WriterInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CompressionWriterInterceptor.class);
    private HttpHeaders httpHeaders;

    public CompressionWriterInterceptor(@Context @NotNull HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public void aroundWriteTo (WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        String encodings = this.httpHeaders.getHeaderString(HttpHeaders.ACCEPT_ENCODING);
//        List<String> encodings = this.httpHeaders.getRequestHeader(HttpHeaders.ACCEPT_ENCODING);

        try {
            final OutputStream outputStream = context.getOutputStream();
            // 如果支持gzip则用gzip压缩再返回
            if(encodings.contains("gzip")) {
                log.trace("Compress the response using gzip.");
                context.setOutputStream(new GZIPOutputStream(outputStream));
            }
        } finally {
            log.trace("Proceed the response.");
            context.proceed();
        }
    }
}