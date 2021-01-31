/**
 * 
 */
package cl.mineduc.came.apoyo_mejora_continua;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Rodrigo Alvarez, Alvaro Tellez
 *
 */
@Configuration
public class ApoyoMejoraContinuaDataSourcesConfiguration {

	@Autowired
	private DataSource dataSource; /**este es el datasource autoconfigurado por properties**/
	
	@Bean(name="transactionManagerApoyoMejoraContinua")
	public PlatformTransactionManager transactionManagerApoyoMejoraContinua(){
		return new DataSourceTransactionManager(this.dataSource);
	}
	
	@Bean(name="sqlSessionFactoryApoyoMejoraContinua")
	@Primary
	public SqlSessionFactory sqlSessionFactoryApoyoMejoraContinua() throws Exception {
	    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	    sessionFactory.setDataSource(this.dataSource);
	    Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("maps/postgresql/*.xml");
	    sessionFactory.setMapperLocations(arrResource);	    
	    return sessionFactory.getObject();
	}
}
