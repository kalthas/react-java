package com.aiexpanse.react.intf.websockets.codec.codec;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import java.io.IOException;
import java.io.Writer;

public abstract class JSONEncoder<T> implements Encoder.TextStream<T> {

    // TODO: check thread-safety
    private ThreadLocal<ObjectMapper> _mapper = ThreadLocal.withInitial(ObjectMapper::new);

    @Override
    public void encode(T t, Writer writer) throws EncodeException, IOException {
        try {
            _mapper.get().writeValue(writer, t);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new EncodeException(t, "Failed to encode", e);
        }
    }

}
