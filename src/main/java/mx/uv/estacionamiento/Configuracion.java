package mx.uv.estacionamiento;

import java.beans.JavaBean;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnablesWs
@Configuration

public class Configuracion extends WsConfigurerAdapter{

    @Bean
    public XsdSchema saludoSchema(){
        return new SimpleXsdSchema(new ClassPathResource("Schema.xsd"));
    }

    @Bean 
    public ServerletRegistrationBean<MessageDispatcherServerlet> MessageDispatcherServerlet(ApplicationContext applicationContext){
        MessageDispatcherServerlet serverlet = new MessageDispatcherServerlet();
        serverlet.setApplicationcontext(applicationContext);
        serverlet.setTransformWsdlLocation(true);
        return new ServerletRegistrationBean<>(serverlet, "/ws/*");
    }

    @Bean(name = "saludos")
public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema saludosSchema){
    defaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
    wsdl.setPortTypeName("saludosPort");
    wsdl.setLocationUri("/ws");
    wsdl.setTargetNamespace("https://t4is.uv.mx/saludos");
    wsdl.setSchema(saludosSchema);
    return wsdl;
    
    }
}

