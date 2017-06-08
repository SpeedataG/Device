package com.example;//package com.example;

import com.devin.TestBean;

public class MyClass {

    public static void main(String [] args){
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.init("TestBean");
        TestBean bean=(TestBean)beanFactory.getBean("TestBean");
        System.out.println("Hello Java!!!");
        System.out.println(bean.toString());
    }

}
