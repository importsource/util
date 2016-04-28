package com.importsource.util.ibalance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * JAVA实现负载均衡
 *
 */
public class TJunheng
{
    
    private static char[] asciis =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r'};
    
    private static final int MAX_SEGMENTS = 7;
    
    public static void main(String[] args)
    {
        HashMap<String, String>[] segments = new HashMap[MAX_SEGMENTS];
        //初始化
        for (int x = 0; x < MAX_SEGMENTS; x++)
        {
            segments[x] = new HashMap<String, String>();
        }
        //求mask
        int ssize = 1;
        while (ssize < MAX_SEGMENTS)
        {
            ssize <<= 1;
        }
        int segmentShift = 32 - MAX_SEGMENTS * 2;
        int segmentMask = ssize - 1;
        //填充数据
        List<String> list = new ArrayList<String>();
        
        for (int k = 0; k < 100000; k++)
        {
            String tmno = getTmno();
            list.add(tmno);
        }
        
        for (String tm : list)
        {
            int hash = hash(tm.hashCode());
            int num = ((hash >>> segmentShift) % MAX_SEGMENTS) & segmentMask;
            segments[num].put(tm, tm);
        }
        //显示各个数组中的大小
        for (int m = 0; m < MAX_SEGMENTS; m++)
        {
            System.out.println(segments[m].size());
        }
        
        System.out.println("---------------------------------");
        
        for (String tm2 : list)
        {
            int hash = hash(tm2.hashCode());
            int num = ((hash >>> segmentShift) % MAX_SEGMENTS) & segmentMask;
            segments[num].put(tm2, tm2);
        }
        //显示各个数组中的大小
        for (int m1 = 0; m1 < MAX_SEGMENTS; m1++)
        {
            System.out.println(segments[m1].size());
        }
    }
    
    private static int hash(int h)
    {
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }
    
    private static String getTmno()
    {
        StringBuilder tmno = new StringBuilder();
        for (int i = 0; i < 10; i++)
        {
            tmno.append(asciis[new Random().nextInt(asciis.length)]);
        }
        for (int j = 0; j < 4; j++)
        {
            tmno.append(new Random().nextInt(9));
        }
        
        return tmno.toString();
    }
}