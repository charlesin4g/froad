package mediinfo.java.tt.froad.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringdocUrlPrinter implements ApplicationListener<ApplicationReadyEvent> {


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Environment env = event.getApplicationContext().getEnvironment();
        String swaggerUiPath = env.getProperty("springdoc.swagger-ui.path");
        String port = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        String host = env.getProperty("server.host", "localhost");

        System.out.println("\n\n➡️ Swagger UI: http://" + host + ":" + port + contextPath + swaggerUiPath);
    }
}
