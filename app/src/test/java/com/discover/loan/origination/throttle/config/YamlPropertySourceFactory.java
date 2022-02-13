// package com.discover.loan.origination.throttle.config;

// import java.io.IOException;
// import java.util.Properties;

// import org.springframework.cloud.config.environment.PropertySource;
// import org.springframework.core.io.support.EncodedResource;
// import org.springframework.core.io.support.PropertySourceFactory;

// public class YamlPropertySourceFactory implements PropertySourceFactory {

//     @Override
//     public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource)
//       throws IOException {
//         YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
//         factory.setResources(encodedResource.getResource());

//         Properties properties = factory.getObject();

//         return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
//     }
// }







