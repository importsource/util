package com.importsource.util.serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



/**
 * Case 1.
 * Jedis does not support cache the Object directly, the Objects needed to be 
 * serialized and de-serialized
 * 
 * Case 2.
 * coming very soon...
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class ListTranscoder<M extends Serializable> extends SerializeTranscoder {
  
  @SuppressWarnings("unchecked")
  public Object deserialize(byte[] in) {
    List<Object> list = new ArrayList<>();
    ByteArrayInputStream bis = null;
    ObjectInputStream is = null;
    long size=0;
    try {
      if (in != null) {
        bis = new ByteArrayInputStream(in);
        is = new ObjectInputStream(bis);
        
        while (true) {
          Object m = is.readObject();
          if (m == null) {
            break;
          }else{
        	  list.add(m);
        	  size++;
          }
          
          
        }
        System.out.println(size);
       
        is.close();
        bis.close();
      }
    } catch (IOException e) {  
      //LoggerUtils.error(logger, String.format("Caught IOException decoding %d bytes of data",  
      //  in == null ? 0 : in.length) + e);  
  } catch (ClassNotFoundException e) {  
    //  LoggerUtils.error(logger, String.format("Caught CNFE decoding %d bytes of data",  
      //  in == null ? 0 : in.length) + e);  
  }  finally {
      close(is);
      close(bis);
    }
    if(size==1){
    	return list.get(0);
    }else{
    	return  list;
    }
    
  }
  

  @SuppressWarnings("unchecked")
  @Override
  public byte[] serialize(Object value) {
	  if(value instanceof List){
		  if (value == null)
		      throw new NullPointerException("Can't serialize null");
		    if(value instanceof List){
		    	List<M> values = (List<M>) value;
		    	 byte[] results = null;
		    	    ByteArrayOutputStream bos = null;
		    	    ObjectOutputStream os = null;
		    	    
		    	    try {
		    	      bos = new ByteArrayOutputStream();
		    	      os = new ObjectOutputStream(bos);
		    	      for (M m : values) {
		    	        os.writeObject(m);
		    	      }
		    	      
		    	      // os.writeObject(null);
		    	      os.close();
		    	      bos.close();
		    	      results = bos.toByteArray();
		    	    } catch (IOException e) {
		    	      throw new IllegalArgumentException("Non-serializable object", e);
		    	    } finally {
		    	      close(os);
		    	      close(bos);
		    	    }
		    	    
		    	    return results;
		    }else{
		    	 if (value == null) {  
		   	      throw new NullPointerException("Can't serialize null");  
		   	    }  
		   	    byte[] result = null;  
		   	    ByteArrayOutputStream bos = null;  
		   	    ObjectOutputStream os = null;  
		   	    try {  
		   	      bos = new ByteArrayOutputStream();  
		   	      os = new ObjectOutputStream(bos);
		   	      M m = (M) value;
		   	      os.writeObject(m);  
		   	      os.close();  
		   	      bos.close();  
		   	      result = bos.toByteArray();  
		   	    } catch (IOException e) {  
		   	      throw new IllegalArgumentException("Non-serializable object", e);  
		   	    } finally {  
		   	      close(os);  
		   	      close(bos);  
		   	    }  
		   	    return result;
	  }
		    
	 
    
    }
	return null;
    
    
   
  }

  
}