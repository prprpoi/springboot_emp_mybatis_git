package com.bluebird;

import com.bluebird.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootEmpMybatisApplicationTests {

    @Autowired//IOC容器对象
    private ApplicationContext applicationContext;

    @Autowired
    private SAXReader saxReader;

    //第三方bean的管理
    @Test
    public void testThirdBean() throws Exception {
        //SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }


    @Test
    public void getBean() {
        //根据名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);

        //根据类型获取
        for (int i = 0; i < 10; i++) {
            DeptController bean2 = applicationContext.getBean(DeptController.class);
            System.out.println(bean2);
        }

        //根据名称和类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);

    }


    @Test
    public void testUuid() {
    }

    /*生成jwt*/
    @Test
    public void testJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "张三");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "bluebird")//设置数字签名的算法
                .setClaims(claims)//存储自定义内容(载荷)
                //设置令牌的有效期,new一个date对象,调用date类型的有参构造方法，传递一个
                // long类型的数据，代表的是毫秒值，加上一个小时的毫秒值
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置令牌有效期为一个小时
                .compact();//拿到字符串类型的返回值（令牌）
        System.out.println(jwt);
    }

    @Test
    /*解析jwt*/
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("bluebird")//指定签名密钥
                //解析JWT令牌
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5byg5LiJIiwiaWQiOjEsImV4cCI6MTY4Mzc5NTUzOH0.CSX8sRr0LRH3r94qLFZXVNa_HRyaTvqMp9pRzidpeSc")
                .getBody();//获取自定义内容
        System.out.println(claims);
    }


}
