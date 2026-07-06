package cl.sebastianrojo.producthub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productHubOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("ProductHub API")
                        .description("API REST para la gestión de productos e inventario.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sebastián Rojo"))
                        .license(new License()
                                .name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/SebastianRed/producthub-api"));
    }
}