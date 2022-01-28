package com.qiang.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author liq
 * @date 2022/1/7 18:33
 */
public class MyMessageConverter implements HttpMessageConverter {
    List<MediaType> supportedMediaTypes = null;

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        if(mediaType == null) { // httpMessageConverter使用clazz判断
            return Object.class.isAssignableFrom(clazz);
        }
        for (MediaType media : supportedMediaTypes) {
            return media.isCompatibleWith(mediaType);
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> mediaTypes = MediaType.parseMediaTypes("application/hello");
        supportedMediaTypes = mediaTypes;
        return mediaTypes;
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        String result = stringToCamel(o);
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    /**
     * 转成snake
     */
    public static String stringToCamel(Object returnValue) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper.writeValueAsString(returnValue);
    }
}
