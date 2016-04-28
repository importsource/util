package com.importsource.util.serverid;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Properties;
import java.util.UUID;
 
/**
 * @author lee
 * 
 * 根据IP地址，JVM虚拟机，时间，同步序列号的情况下产生的相对唯一ID，可用于多线程，多服务器。
 * 32位 系统的 UUID 生成为36位
 */
public class UUIDHexGenerator extends AbstractUUIDGenerator {
	public static final UUIDHexGenerator DEFAULT = new UUIDHexGenerator();
	private String sep = "";
 
	protected String format(final int intval) {
		final String formatted = Integer.toHexString(intval);
		final StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}
 
	protected String format(final short shortval) {
		final String formatted = Integer.toHexString(shortval);
		final StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
 
	public Serializable generate(final Object obj) {
		return new StringBuffer(36).append(format(getIP())).append(sep)
				.append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep)
				.append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}
 
	public void configure(final Properties params) {
		sep = params.getProperty("separator", "");
	}
 
	public static final String generator() {
		return String.valueOf(UUIDHexGenerator.DEFAULT.generate(null));
	}
 
	public static final String generator(final Object obj) {
		return String.valueOf(UUIDHexGenerator.DEFAULT.generate(obj));
	}
 
	public static void main(final String[] args) {
		String s=UUIDHexGenerator.generator();
		System.out.println(s.length());
		System.out.println(UUIDHexGenerator.generator());
		System.out.println(UUIDHexGenerator.generator());
		String s2=UUID.randomUUID().toString();
		System.out.println(s2);
		System.out.println(s2.length());
	}
}
 
abstract class AbstractUUIDGenerator {
 
	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (final Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
 
	public static int toInt(final byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}
 
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
 
	public AbstractUUIDGenerator() {
	}
 
	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}
 
	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (AbstractUUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}
 
	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}
 
	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}
 
	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}
}