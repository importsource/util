package com.importsource.util.map1;
import java.util.Arrays;

public class Test {

 /**
  * Java提供的数组复制的方法
  */
 public static void main(String[] args) {
  char[] cArr = { '北', '京' };
  char[] temp = Arrays.copyOf(cArr, 4);// 新数组长度为4，后两位自动赋值0
  char[] cArr2 = temp;
  cArr2[2] = '帅';
  cArr2[3] = '哥';
  System.out.println(cArr2);
  //如果想看数组元素内容，以后统一使用Arrays.toString(arr)形式输出即可
 }
}