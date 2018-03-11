package com.aiexpanse.react.intf.websockets.codec;

import com.aiexpanse.react.intf.websockets.guice.InjectConfigurator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class JSONDecoder<T> implements Decoder.TextStream<T> {

    private Class<T> _type;

    // TODO: check thread-safety
    private ThreadLocal<ObjectMapper> _mapper = new ThreadLocal<>();

    @Override
    public void init(EndpointConfig endpointConfig) {
        Injector injector = (Injector) endpointConfig.getUserProperties().get(InjectConfigurator.INJECTOR);
        _mapper.set(injector.getInstance(ObjectMapper.class));
        ParameterizedType clazz = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type type = clazz.getActualTypeArguments()[0];
        if (type instanceof Class) {
            _type = (Class<T>)type;
        }
        else if (type instanceof ParameterizedType) {
            _type = (Class<T>)((ParameterizedType)type).getRawType();
        }
    }

    @Override
    public T decode(Reader reader) throws DecodeException, IOException {
        try {
            return _mapper.get().readValue(reader, _type);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new DecodeException(reader.toString(), "Failed to decode", e);
        }
    }

}
