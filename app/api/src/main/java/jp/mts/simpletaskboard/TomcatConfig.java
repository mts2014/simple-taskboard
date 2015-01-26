package jp.mts.simpletaskboard;

import jp.mts.simpletaskboard.profiles.CooperateWebServer;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@CooperateWebServer
public class TomcatConfig {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
	    tomcat.addAdditionalTomcatConnectors(createAjpConnector());
	    return tomcat;
	}

	private Connector createAjpConnector() {
	    Connector connector = new Connector("org.apache.coyote.ajp.AjpProtocol");
	    connector.setScheme("ajp");
	    connector.setProtocol("AJP/1.3");
	    connector.setRedirectPort(8443);
	    connector.setPort(8009);
	    return connector;
	}
}
