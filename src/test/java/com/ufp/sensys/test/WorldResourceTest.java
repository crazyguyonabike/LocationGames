package com.ufp.sensys.test;

import java.io.InputStream;
import java.io.FileInputStream;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.WebAppDescriptor.Builder;

import org.springframework.web.context.ContextLoaderListener;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class WorldResourceTest extends JerseyTest {

    public WorldResourceTest() throws Exception {
        super(new WebAppDescriptor.Builder("com.ufp.sensys.resource")
              .contextPath("world")
              .servletClass(SpringServlet.class)
              .contextListenerClass(ContextLoaderListener.class)
              .contextParam("contextConfigLocation", "classpath:applicationContext.xml").build());
    }

    @Test
    public void test() throws Exception {
        WebResource webResource = resource();

        ClientResponse clientResponse = webResource.path("/ad").type(MediaType.APPLICATION_OCTET_STREAM).post(ClientResponse.class, "37.874007,-122.268605,Oscar's,10% off any combo meal");
        assertEquals(204, clientResponse.getStatus());

        InputStream inputStream = new FileInputStream("src/test/resources/test.csv");
        clientResponse = webResource.path("/").post(ClientResponse.class, inputStream);
        assertEquals(204, clientResponse.getStatus());
    }
}