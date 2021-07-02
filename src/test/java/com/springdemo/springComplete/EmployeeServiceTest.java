package com.springdemo.springComplete;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.springdemo.springComplete.dao.EmployeeDao;
import com.springdemo.springComplete.entity.Employee;
import com.springdemo.springComplete.service.EmployeeService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

		@MockBean
	    private EmployeeDao daoMock;

		
	
	    @Autowired
		private EmployeeService service1;
		
	    @Before
	    public void setUp() throws Exception {
	    }
	    
	    private int add(int a, int b) {
	    	return a+b;
	    }
	    
	    @Test
	    public void testSoemFun() {
	    	 assertThat(add(1,1), is(2));
	    }
	    
	    
//		@Ignore
	    @Test
		public void testGetEmployees() throws SQLException {
	    	
	    	Employee e1=new Employee();
			e1.setEmployeeId(1001);
			e1.setName("rakesh");
	    	Employee e2=new Employee();
			List <Employee> employees=new ArrayList<Employee>();
			employees.add(e1);
			employees.add(e2);
	    	
	    		when(daoMock.findAll()).thenReturn(employees);
	    		assertThat(service1.getEmploees(), hasSize(2));
	    	  }
	    
	    private List<Employee> getTestData(){
	    	Employee e1=new Employee();
			e1.setEmployeeId(1001);
			e1.setName("rakesh");
	    	Employee e2=new Employee();
			List <Employee> employees=new ArrayList<Employee>();
			employees.add(e1);
			employees.add(e2);
			return employees;
		}
	    
	
}
