package br.com.uanderson.blackboxannotations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageWebConfiguration implements WebMvcConfigurer {
    public final static String IMAGE_RESOURCE_BASE = "/save";
    public final static String IMAGE_FILE_BASE = "src/main/resources/static/imagens/img-uploads";
    public final static String BASE_URL = "http://localhost:9090/v1/app/cpd/postagens";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMAGE_RESOURCE_BASE + "/**")
                .addResourceLocations("file:" + IMAGE_FILE_BASE);

        /*
        O método addResourceHandlers() permite mapear localizações de arquivos
        no servidor para caminhos de URL.
         */
    }


}
