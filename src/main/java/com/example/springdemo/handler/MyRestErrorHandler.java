/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * RestTemplate自定义异常处理 .
 *
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Slf4j
public class MyRestErrorHandler implements ResponseErrorHandler {
    /**
     * 判断返回结果是否存在异常结果 .
     * @param clientHttpResponse 返回值
     * @return 是否存在异常
     * @throws IOException IOException异常
     */
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        int rawStatusCode = clientHttpResponse.getRawStatusCode();
        HttpStatus statusCode = HttpStatus.resolve(rawStatusCode);
//        return statusCode != null ? this.hasError(statusCode) : this.hasError(rawStatusCode);
        return (statusCode != null ? statusCode.isError(): hasError(rawStatusCode));
    }

    /**
     * 判断错误是否为4**、5** 错误 .
     * @param unknownStatusCode 未知的错误请求头
     * @return 是否为客户端或者服务端错误
     */
    protected boolean hasError(int unknownStatusCode) {
        HttpStatus.Series series = HttpStatus.Series.resolve(unknownStatusCode);
        return (series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        // 里面可以实现你自己遇到了Error进行合理的处理
        //TODO 将接口请求的异常信息持久化
        log.error("请求错误");
        HttpStatus statusCode = HttpStatus.resolve(clientHttpResponse.getRawStatusCode());
        String statusText = clientHttpResponse.getStatusText();
        HttpHeaders headers = clientHttpResponse.getHeaders();
        byte[] body = this.getResponseBody(clientHttpResponse);
        Charset charset = this.getCharset(clientHttpResponse);
        String message = this.getErrorMessage(statusCode.value(), statusText, body, charset);
        switch(statusCode.series()) {
            case CLIENT_ERROR:
                message = "客户端错误";
                throw HttpClientErrorException.create(message, statusCode, statusText, headers, body, charset);
            case SERVER_ERROR:
                throw HttpServerErrorException.create(message, statusCode, statusText, headers, body, charset);
            default:
                throw new UnknownHttpStatusCodeException(message, statusCode.value(), statusText, headers, body, charset);
        }
    }
    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException var3) {
            return new byte[0];
        }
    }

    @Nullable
    protected Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharset() : null;
    }
    private String getErrorMessage(int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset) {
        String preface = rawStatusCode + " " + statusText + ": ";
        if (ObjectUtils.isEmpty(responseBody)) {
            return preface + "[no body]";
        } else {
            charset = charset == null ? StandardCharsets.UTF_8 : charset;
            int maxChars = 200;
            if (responseBody.length < maxChars * 2) {
                return preface + "[" + new String(responseBody, charset) + "]";
            } else {
                try {
                    Reader reader = new InputStreamReader(new ByteArrayInputStream(responseBody), charset);
                    CharBuffer buffer = CharBuffer.allocate(maxChars);
                    reader.read(buffer);
                    reader.close();
                    buffer.flip();
                    return preface + "[" + buffer.toString() + "... (" + responseBody.length + " bytes)]";
                } catch (IOException var9) {
                    throw new IllegalStateException(var9);
                }
            }
        }
    }
}
