package com.springdemo.springComplete;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import com.springdemo.springComplete.entity.Employee;
import com.springdemo.springComplete.service.EmployeeService;

import org.junit.jupiter.api.Test;



@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceIntegration {

		@Autowired
	 	private MockMvc mockMvc;
	    
	    private List<Employee> employees;
	
		private EmployeeService service1;
		
	     
	////	@Ignore
	//    @Test
		public void testGetEmployees() throws Exception {
	    	System.out.println(service1);
	    		when(service1.getEmploees()).thenReturn(getTestData());
	    		mockMvc.perform(get("/employees")
//	    				.with(httpBasic("user", "password"))
	    				.contentType(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$[0].employeeId", is(1001)))
	             .andExpect(jsonPath("$[1].employeeId", is(1002)))
	             .andDo(print());
	    
	    }
	    
	    
	    @Test
		public void testGetEmployeeById() throws Exception {
	    	System.out.println(service1);
	    		when(service1.getEmployeeById(1001)).thenReturn(getEmployee());
	    		mockMvc.perform(get("/employees/1001")
//	    				.with(httpBasic("user", "password"))
	    				.contentType(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk())
	             .andExpect(jsonPath("$.employeeId", is(1001)))
	             .andExpect(jsonPath("$.name", is("rakesh")))
	             .andDo(print());
	    
	    }
	    
	    
	   // @Test
		public void testGetEmployeeByIdFailure() throws Exception {
	    	System.out.println(service1);
	    		when(service1.getEmployeeById(1001)).thenReturn(getEmployee());
	    		mockMvc.perform(get("/employees/1001")
//	    				.with(httpBasic("user", "password"))
	    				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	    }
	    
	    
	    private Employee getEmployee(){
	    	Employee e1=new Employee();
			e1.setName("rakesh");
			e1.setEmployeeId(1001);
		
			return e1;
		}
	    
	    private List<Employee> getTestData(){
	    	Employee e1=new Employee();
			e1.setName("rakesh");
			e1.setEmployeeId(1001);
			Employee e2=new Employee();
			e2.setName("rohit");
			e2.setEmployeeId(1002);
			List <Employee> employees=new ArrayList<Employee>();
			employees.add(e1);
			employees.add(e2);
			return employees;
		}
	    
	
}
