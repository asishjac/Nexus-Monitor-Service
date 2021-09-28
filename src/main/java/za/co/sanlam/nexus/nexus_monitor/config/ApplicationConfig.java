package za.co.sanlam.nexus.nexus_monitor.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties","classpath:appsecurity.properties"})
public class ApplicationConfig {

    @Value("${nexus.url}")
    public String nexusUrl;

    @Value("${nexus.username}")
    public String nexusUsername;

    @Value("${nexus.password}")
    public String nexusPassword;

    @Value("${nexus.rest.api.url}")
    public String nexusRestApiUrl;

    @Value("${nexus.rest.api.script.url}")
    public String nexusRestApiScriptUrl;

    @Value("${nexus.rest.api.script.run.url}")
    public String nexusRestApiScriptRunUrl;

    @Value("${nexus.storage.script.name}")
    public String nexusStorageScriptName;

    @Value("${nexus.storage.script.type}")
    public String nexusStorageScriptType;

    @Value("${nexus.storage.script.location}")
    public String nexusStorageScriptLocation;


}
