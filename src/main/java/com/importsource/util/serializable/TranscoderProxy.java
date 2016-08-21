package com.importsource.util.serializable;

import java.io.Serializable;
import java.util.List;

public class TranscoderProxy extends SerializeTranscoder {
	
	ListTranscoder<Serializable> listTranscoder=new ListTranscoder<>();
	ObjectsTranscoder<Serializable> objectTranscoder=new ObjectsTranscoder<>();
	
	@Override
	public byte[] serialize(Object value) {
		if(value instanceof List){
			return listTranscoder.serialize(value);
		}
		if(value instanceof Serializable &&!( value instanceof List)){
			return objectTranscoder.serialize(value);
		}
		return null;
	}

	@Override
	public Object deserialize(byte[] in) {
		return listTranscoder.deserialize(in);
	}
    
}
