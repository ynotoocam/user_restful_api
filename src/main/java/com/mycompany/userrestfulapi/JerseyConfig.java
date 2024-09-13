package com.mycompany.userrestfulapi;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.ApplicationPath;
import io.swagger.v3.jaxrs2.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("UserRestfulApi/webapi")
@OpenAPIDefinition(info = @Info(title="User Restful API", version = "1.0"))
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        packages("com.restful.resources");
        register(OpenApiResource.class);
        register(SwaggerSerializers.class);
    }
    
}
