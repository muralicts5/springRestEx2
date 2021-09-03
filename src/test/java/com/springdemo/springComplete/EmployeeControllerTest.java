
package com.springdemo.springComplete;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.springComplete.entity.Employee;
import com.springdemo.springComplete.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;



@SpringBootTest
@AutoConfigureMockMvc	
public class EmployeeControllerTest {

		@Autowired
	 	private MockMvc mockMvc;
	    
	  
	    private List<Employee> employees;
	
	    @MockBean
		private EmployeeService service1;
		
	     
	////	@Ignore
	//     @Test
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
		   // @Test
			public void testPostEmployeeBy() throws Exception {
		    	System.out.println(service1);
		    	Employee e1=new Employee();
				e1.setName("rakesh");
				e1.setEmployeeId(1001);
		    		when(service1.insertEmployee(e1)).thenReturn(getEmployee());
		    		mockMvc.perform(post("/employees")
		    			.content(asJsonString(e1))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated());
	}
	 
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	    
	
}

/*
 * 
	    
	    @Test(expected=BussinessException.class)
		public void testGetEmployeesException() throws SQLException {
	    		when(daoMock.getEmployees()).thenThrow(SQLException.class);
	    		service1.getEmployees();
	   	  }
	    
	    @Ignore
	     @Test
		public void testSave() {
	    		when(daoMock.saveEmployee(any(Employee.class)))
	    					.thenReturn(true);
	    		//assertTrue(service1.insertEmployee(new Employee()));
	    		assertThat(service1.insertEmployee(new Employee()),is(true));
		}

	    @Ignore
	    @Test
	 	public void testGetEmployeeById() {
	    			Employee dummy=new Employee();
	    			dummy.setId(4534);
	     		
	    			when(daoMock.getEmployeeById(any(Integer.class)))
	     			.thenReturn(Optional.of(dummy));
	    			
	    			Employee d=service1.getEmployeeById(1001);
	     		System.out.println(d.getId());
	     		assertThat(service1.getEmployeeById(1001), is(notNullValue())); 	
	     		}
	    
	    
	    @Ignore
	    @Test
	 	public void testGetEmployeeByIdAnswer() {
	     		when(daoMock.getEmployeeById(any(Integer.class)))
	     			.thenAnswer(
	     					 invocation->{
	     						 Object[] arguments = invocation.getArguments();
	     		                if (arguments != null && arguments.length > 0 && arguments[0] != null){
	     		                		int id=(Integer)arguments[0];
	     		                		if(id>0)
	     		                			return Optional.of(new Employee());
	     		                	}
	     		                	return Optional.empty();
	     					 });
	     		assertThat(service1.getEmployeeById(1), is(notNullValue())); 
	     		}
	    
	    
	    
	    @Ignore
	    @Test(expected = EmployeeNotFoundException.class)
	    public void testGetEmployeeByIdException() {
	    	when(daoMock.getEmployeeById(any(Integer.class)))
	    				.thenReturn(Optional.empty());
	    			//	.thenReturn(Optional.of(new Employee()));
	     	service1.getEmployeeById(34);  
	    }
	    
	    
	    @Ignore
	    @Test
	    public void testFindTopOneEmployee() throws SQLException {
			when(daoMock.getEmployees()).thenReturn(getTestData());
				Employee e=service1.findTopOneEmployee();
				System.out.println(e.getName());
				//assertNotNull(e);
				//assertEquals(e.getName(), "ramesh");
		}*/
