package com.springdemo.springComplete;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import com.springdemo.springComplete.entity.Employee;
import org.junit.Ignore;


@RunWith(SpringRunner.class)
@SpringBootTest
//@WithMockUser(username="user",roles={"USER","ADMIN"})
@AutoConfigureMockMvc
public class SpringCompleteApplicationTests {

	
	@Before
	public void setUp() throws Exception {
	}

	
	@Autowired
	private MockMvc mockMvc;
	
	@Ignore
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
    	mockMvc.perform(get("/employees")
    			//.with(httpBasic("user", "password"))
    			.contentType(MediaType.APPLICATION_JSON)
    			)
    		.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].salary", is(1105470.0)))
        .andDo(print());
      
    }
}
