package com.importsource.util.xio.nio.socketchannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * TCPProtocol的实现类
 * 
 * @date    2010-2-3
 * @time    上午08:58:59
 * @version 1.00
 */
public class TCPProtocolImpl implements TCPProtocol{
  private int bufferSize;
  
  public TCPProtocolImpl(int bufferSize){
    this.bufferSize=bufferSize;
  }

  public void handleAccept(SelectionKey key) throws IOException {
    SocketChannel clientChannel=((ServerSocketChannel)key.channel()).accept();
    clientChannel.configureBlocking(false);
    clientChannel.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(bufferSize));
  }

  public void handleRead(SelectionKey key) throws IOException {
    // 获得与客户端通信的信道
    SocketChannel clientChannel=(SocketChannel)key.channel();
    
    // 得到并清空缓冲区
    ByteBuffer buffer=(ByteBuffer)key.attachment();
    buffer.clear();
    
    // 读取信息获得读取的字节数
    long bytesRead=clientChannel.read(buffer);
    
    if(bytesRead==-1){
      // 没有读取到内容的情况
      clientChannel.close();
    }
    else{
      // 将缓冲区准备为数据传出状态
      buffer.flip();
      
      // 将字节转化为为UTF-16的字符串   
      String receivedString=Charset.forName("UTF-16").newDecoder().decode(buffer).toString();
      
      // 控制台打印出来
      System.out.println("接收到来自"+clientChannel.socket().getRemoteSocketAddress()+"的信息:"+receivedString);
      
      // 准备发送的文本
      String sendString="你好,客户端. @"+new Date().toString()+"，已经收到你的信息"+receivedString;
      buffer=ByteBuffer.wrap(sendString.getBytes("UTF-16"));
      clientChannel.write(buffer);
      
      // 设置为下一次读取或是写入做准备
      key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
  }

  public void handleWrite(SelectionKey key) throws IOException {
    // do nothing
  }
}