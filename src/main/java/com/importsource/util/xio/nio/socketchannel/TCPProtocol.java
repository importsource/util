package com.importsource.util.xio.nio.socketchannel;
import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * TCPServerSelector与特定协议间通信的接口
 * 
 * @date    2010-2-3
 * @time    上午08:42:42
 * @version 1.00
 */
public interface TCPProtocol{
  /**
   * 接收一个SocketChannel的处理
   * @param key
   * @throws IOException
   */
  void handleAccept(SelectionKey key) throws IOException;
  
  /**
   * 从一个SocketChannel读取信息的处理
   * @param key
   * @throws IOException
   */
  void handleRead(SelectionKey key) throws IOException;
  
  /**
   * 向一个SocketChannel写入信息的处理
   * @param key
   * @throws IOException
   */
  void handleWrite(SelectionKey key) throws IOException;
}