package com.zsx.d_attributein.c_p;

public class DCtest {
/**
 * 方式与setter注入没有什么区别，只是将参数的注入放到了bean里面
 * 该操作需要配置约束
 * p命名空间
 * 	1.复制第二行的 xmlns="http://www.springframework.org/schema/beans"
 * 	2.将其改为        xmlns:p="http://www.springframework.org/schema/p" 并放在其他行
 * 	3.在bean标签里写上  p:pname="" p:page="" p:address-ref="" 将参数注入进去
 */
}
