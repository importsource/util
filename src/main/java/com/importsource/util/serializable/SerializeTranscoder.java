package com.importsource.util.serializable;
import java.io.Closeable;



/**
 * @author Hezf
 *
 */
public abstract class SerializeTranscoder {

  
  public abstract byte[] serialize(Object value);
  
  public abstract Object deserialize(byte[] in);
  
  public void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception e) {
         //logger.info("Unable to close " + closeable, e); 
      }
    }
  }
}