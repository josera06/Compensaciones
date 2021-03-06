package mx.com.jrc.Compensaciones;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class CompensacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompensacionesApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Setting Spring Boot SetTimeZone
		//TimeZone.setDefault(TimeZone.getTimeZone("CST7CDT"));
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-05:00"));
	}

	/**
	 * Convierta la solicitud http en una solicitud https
	 * @return
	 */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}
	/**
	 * Convierta la solicitud http en una solicitud https
	 * @return
	 */
	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8083);
		return connector;
	}
}
