package com.circle.configuration;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.circle")
@PropertySource(value={"classpath:hibernate.properties"})
@EnableTransactionManagement

public class AppConfiguration extends WebMvcConfigurerAdapter{
	   
	 private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	 private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	 private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	 private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	      
	 private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	 private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	 private static final String PROPERTY_NAME_HIBERNATE_HBMTODDL = "hibernate.hbm2ddl.auto";
	 @Resource
	 private Environment env;
	 
	 

	    /**
	     * Configure TilesConfigurer.
	     */
	    @Bean
	    public TilesConfigurer tilesConfigurer(){
	        TilesConfigurer tilesConfigurer = new TilesConfigurer();
	       tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
	        tilesConfigurer.setCheckRefresh(true);
	        return tilesConfigurer;
	    }
	 
	    /**
	     * Configure ViewResolvers to deliver preferred views.
	     */
	    @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        TilesViewResolver viewResolver = new TilesViewResolver();
	        registry.viewResolver(viewResolver);
	    }
	     
/*	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
 		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
 		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");

		/*registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);*/
	}
/*	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
	}*/
	 @Bean(name = "datasource")
	    public  DataSource dataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
      
      dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
      dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
      dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
      dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
      return dataSource;
	    }
	 @Bean
	    public LocalSessionFactoryBean sessionFactory(){
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[]{"com.circle.model"});
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	        
	    }
	 private Properties hibernateProperties() {
		 Properties properties = new Properties();
      properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
      properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
      properties.put(PROPERTY_NAME_HIBERNATE_HBMTODDL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBMTODDL));
      return properties;       
	    }
	 @Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
	        HibernateTransactionManager tx = new HibernateTransactionManager();
	        tx.setSessionFactory(sessionFactory);
	        return tx;
	    }
	 
/*	 @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
          configurer.favorPathExtension(true)
                  .useJaf(false)
                  .ignoreAcceptHeader(true)
                  .mediaType("html", MediaType.TEXT_HTML)
                  .mediaType("json", MediaType.APPLICATION_JSON)
                  .defaultContentType(MediaType.APPLICATION_JSON);
  }
	  */ 
	  /*  @Override
	    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(mappingJackson2HttpMessageConverter());
	    }
	     
	    @Bean
	    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	        converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
	        return converter;
	    }*/
	     
	 @Bean(name ="messageSource")
	    public MessageSource getMessageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("/WEB-INF/i18/circleMessage");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	 @Bean(name="localeResolver")
	 public LocaleResolver localeResolver(){
	 SessionLocaleResolver  resolver = new SessionLocaleResolver ();
	    resolver.setDefaultLocale(new Locale("en"));
	    return resolver;
	 } 
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("mylocale");
		registry.addInterceptor(interceptor);
	    }
}