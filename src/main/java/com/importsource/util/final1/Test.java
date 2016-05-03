package com.importsource.util.final1;
import java.util.ArrayList;
import java.util.List;
/**
 * Final keyword Test
 * @author Hezf
 *
 */
class Test {
    private final List foo;

    public Test() {
        foo = new ArrayList();
        foo.add("foo"); // Modification-1
    }

    public void setFoo(List foo) {
//      this.foo = foo;  //compile time error.这里你把注释去掉，编译器无法编译通过
    }
}