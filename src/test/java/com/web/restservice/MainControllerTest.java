package com.web.restservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainControllerTest extends AbstractTest {
   
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void testStandartParams() throws Exception {
      String uri = "/calendar";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
      .andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
   }
   @Test
   public void testNegativeYear() throws Exception {
      String uri = "/calendar";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .param("year", "-100").param("day", "1")).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
   }
   @Test
   public void testNegativeDay() throws Exception {
    String uri = "/calendar";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
       .param("year", "2000").param("day", "-10")).andReturn();
    
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
   }
   @Test
   public void testBigYear() throws Exception {
    String uri = "/calendar";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
       .param("year", "2000000000000000000000000000000000").param("day", "2")).andReturn();
    
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
   }
   @Test
   public void testCounter() throws Exception {
      String uri = "/counter";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri))
      .andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
   }
}