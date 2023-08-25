package com.tao;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * @author tyb
 * @Description
 * @create 2021-09-14 10:13
 */
public class SpringApplication {
    public static void main(String[] args) {
        run(SpringApplication.class, args);
    }

    private static void run(Class<SpringApplication> springApplicationClass, String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            final String sourcePath = "";
            String basePath = System.getProperty("user.dir") + File.separator + sourcePath + File.separator;
            tomcat.getHost().setAppBase(basePath);

            //改变文件读取路径，从resources目录下去取文件
            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", basePath + "src" + File.separator + "main" + File.separator + "resources");
            // 禁止重新载入
            ctx.setReloadable(false);
            // class文件读取地址
            File additionWebInfClasses = new File(sourcePath + "/target/classes");
            // 创建WebRoot
            WebResourceRoot resources = new StandardRoot(ctx);
            // tomcat内部读取Class执行
            resources.addPreResources(
                    new DirResourceSet(resources, sourcePath + "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            tomcat.setPort(8080);
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception ex) {

        }
    }
}
