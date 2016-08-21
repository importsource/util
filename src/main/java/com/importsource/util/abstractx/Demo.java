/**
 * 
 */
package com.importsource.util.abstractx;

/**
 * @author Hezf
 *
 */
public class Demo {
    public static void main(String[] args){
    	User user=new User();
    	say(user);
    }
    
    
    public static void say(AbstractUser user){
    	user.say();
    }
}
