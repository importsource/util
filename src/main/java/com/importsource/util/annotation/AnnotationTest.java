package com.importsource.util.annotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationTest {

    @Field_Method_Parameter_Annotation(describ="字段编号",type=int.class)  //注释字段
    int id;
    @Field_Method_Parameter_Annotation(describ="字段姓名",type=String.class)//注释字段
    String name;
    
    @Constructor_Annotation()//采用默认构造方法
    public AnnotationTest()
    {
        
    }
    @Constructor_Annotation("立即初始化构造方法.")    //注释构造方法
    public AnnotationTest(
    //注释构造方法参数
    @Field_Method_Parameter_Annotation(describ="编号",type=int.class)
    int id,
    @Field_Method_Parameter_Annotation(describ="姓名",type=String.class)
    String name
    )
    {
        this.id = id;
        this.name = name;
    }
    
    @Field_Method_Parameter_Annotation(describ="获得编号",type=int.class)
    public int getId()
    {
        return id;
    }
    @Field_Method_Parameter_Annotation(describ="设置编号")   //成员type，采用默认注释方法
    public void setId(
    //注释参数
    @Field_Method_Parameter_Annotation(describ="设置编号",type=int.class)
    int id
    )
    {
        this.id =id;
    }
    @Field_Method_Parameter_Annotation(describ="获得姓名",type=String.class)
    public String getName()
    {
        return name;
    }
    @Field_Method_Parameter_Annotation(describ="设置姓名")
    public void setName(
    @Field_Method_Parameter_Annotation(describ="姓名",type=String.class)
    String name
    )
    {
        this.name = name;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //构造方法：
        Constructor[] declaredConstructor = AnnotationTest.class.getDeclaredConstructors();  //获得所有的构造方法
        for(int i=0;i<declaredConstructor.length;i++)
        {
            Constructor  constructor = declaredConstructor[i];   //遍历构造方法
            if(constructor.isAnnotationPresent(Constructor_Annotation.class))   //查看是否指定类型的注释
            {
            Constructor_Annotation ca = (Constructor_Annotation)constructor.getAnnotation(Constructor_Annotation.class);
            System.out.println("ca.value()=: "+ca.value());
            }
        
            Annotation[][]parameterAnnotations = constructor.getParameterAnnotations();//获得参数注释 
            for(int j=0;j<parameterAnnotations.length;j++)
            {
                int length = parameterAnnotations[j].length;
                if(length == 0)   //如果为0，则表示没有为该参数添加注释
                {
                    System.out.println("没有为该参数添加注释");
                }
                else
                {
                    for(int k=0;k<length;k++)
                    {
                        //获得参数注释
                        Field_Method_Parameter_Annotation pa = (Field_Method_Parameter_Annotation)parameterAnnotations[j][k];
                        System.out.print(" "+pa.describ());   //参数描述
                        System.out.println(" "+pa.type());      //参数类型
                    }
                }
            }
            System.out.println("****************");
        }
        
        
        //字段：
        System.out.println("********字段的Annotation*************");
        Field[] declaredFields = AnnotationTest.class.getDeclaredFields();   //获得所有的字段
        for(int i=0;i<declaredFields.length;i++)
        {
            Field field = declaredFields[i];
            //查看是否具有指定类型的注释：
            if(field.isAnnotationPresent(Field_Method_Parameter_Annotation.class))
            {
                Field_Method_Parameter_Annotation fa = (Field_Method_Parameter_Annotation)field.getAnnotation(Field_Method_Parameter_Annotation.class);
                System.out.print(" "+fa.describ());   //获得字段描述
                System.out.println(" "+fa.type());    //获得字段类型
            }
        }
        
        //方法
        System.out.println("********方法的Annotation*************");
        Method [] methods = AnnotationTest.class.getDeclaredMethods();    //获得所有的方法
        for(int i=0;i<methods.length;i++)
        {
            Method method = methods[i];
            //查看是否指定注释：
            if(method.isAnnotationPresent(Field_Method_Parameter_Annotation.class))  
                
            {
                Field_Method_Parameter_Annotation ma = (Field_Method_Parameter_Annotation)method.getAnnotation(Field_Method_Parameter_Annotation.class);
                System.out.print(" "+ma.describ());   //获得方法描述
                System.out.println(" "+ma.type());    //获得方法类型
            }
        
            Annotation[][]parameterAnnotations = method.getParameterAnnotations();    //获得所有参数
            for(int j=0;j<parameterAnnotations.length;j++)
            {
                int length = parameterAnnotations[j].length; 
                if(length==0)
                {
                    System.out.println("没有添加Annotation参数");
                }
                else
                {
                    for(int k=0;k<length;k++)
                    {
                        //获得指定的注释：
                        Field_Method_Parameter_Annotation pa = (Field_Method_Parameter_Annotation)parameterAnnotations[j][k];
                        System.out.print(" "+pa.describ());   //获得参数描述
                        System.out.println(" "+pa.type());    //获得参数类型
                    }
                }
            }
            System.out.println("********************");
            
        }
    }

}