package com.importsource.util.comparable;

/**
 * 
 * @author Hezf
 *
 */
public class ComparableDemo01 {

    public static void main(String[] args) {
        Student stu[]={new Student("zhangsan",20,90.0f),
                new Student("lisi",22,90.0f),
                new Student("wangwu",20,99.0f),
                new Student("sunliu",22,100.0f),
                new Student("sunwu",22,101.0f)        
        };
        java.util.Arrays.sort(stu);
        for(Student s:stu)
        {
            System.out.println(s);
        }
    }
}