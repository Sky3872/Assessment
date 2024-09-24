package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.demo.filter.LoggingFilter;

@SpringBootApplication
public class DemoApplication {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//	
//	@Autowired
//	private CustomerProfileRepository customerProfileRepository;

	
	public static void main(String[] args) {
//		String connectionUrl = "jdbc:sqlserver://localhost;database=TESTDB;user=sysuser;password=Passw0rd;encrypt=false;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//		 try (Connection connection = DriverManager.getConnection(connectionUrl)) {
//	            System.out.println("Connected to SQL Server successfully.");
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
	
//	@Override
//	public void run(String... args) throws Exception{
//		List<CustomerProfileEntity> cplist = customerProfileRepository.findAll();
//		System.out.println("===========================================================");
//		System.out.println("===========================================================");
//		System.out.println("===========================================================");
//		cplist.forEach(System.out :: println);
//		System.out.println("===========================================================");
//		System.out.println("===========================================================");
//		System.out.println("===========================================================");
//		
////		String sql = "SELECT * FROM customer_profile";
////		List<CustomerProfileEntity> cplist = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CustomerProfileEntity.class));
////		
////		cplist.forEach(System.out :: println);
//	}

}
