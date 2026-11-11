package com.basicfood.web;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * Arranca Tomcat en local para servir las JSP y los servlets.
 *
 * En producción no se usa esta clase: el WAR se despliega en un Tomcat normal
 * (ver Dockerfile). Aquí solo evita tener que instalar Tomcat en el portátil.
 */
public class WebMain {

    public static void main(String[] args) throws Exception {
        int puerto = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        String docBase = System.getenv().getOrDefault("WEBAPP_DIR", "src/main/webapp");

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("target/tomcat");
        tomcat.setPort(puerto);
        tomcat.getConnector();

        Context contexto = tomcat.addWebapp("", new File(docBase).getAbsolutePath());

        // exec:java carga la app con su propio classloader. Si no se lo damos a
        // Tomcat, el webapp classloader busca en el classloader del sistema y no
        // encuentra Jasper ni la API de servlets.
        contexto.setParentClassLoader(WebMain.class.getClassLoader());

        // En local las clases compiladas están en target/classes, no en el WAR.
        // Se las damos a Tomcat como si fueran /WEB-INF/classes para que
        // encuentre los servlets con @WebServlet.
        WebResourceRoot recursos = new StandardRoot(contexto);
        recursos.addPreResources(new DirResourceSet(
                recursos,
                "/WEB-INF/classes",
                new File("target/classes").getAbsolutePath(),
                "/"));
        contexto.setResources(recursos);

        tomcat.start();
        System.out.println();
        System.out.println("  BasicFood en marcha: http://localhost:" + puerto);
        System.out.println("  Pulsa Ctrl+C para parar.");
        System.out.println();

        tomcat.getServer().await();
    }
}
