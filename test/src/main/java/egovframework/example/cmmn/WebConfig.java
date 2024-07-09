package egovframework.example.cmmn;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded/img/**")
                .addResourceLocations("file://C:/Users/ltree/git/egovframetest/test/src/main/webapp/uploaded/img/");
    }
}
