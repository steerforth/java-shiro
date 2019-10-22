package com.steer.phoenix.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = BeetlProperties.BEETLCONF_PREFIX)
@Data
public class BeetlProperties {
    public static final String BEETLCONF_PREFIX = "beetl";

    private String delimiterStatementStart;

    private String delimiterStatementEnd;

    private String resourceTagroot;

    private String resourceTagsuffix;

    private String resourceAutoCheck;

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    public Properties getProperties() {
        Properties properties = new Properties();
        if (!StringUtils.isEmpty(delimiterStatementStart)) {
            if (delimiterStatementStart.startsWith("\\")) {
                delimiterStatementStart = delimiterStatementStart.substring(1);
            }
            properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        }
        if (!StringUtils.isEmpty(delimiterStatementEnd)) {
            properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
        } else {
            properties.setProperty("DELIMITER_STATEMENT_END", "null");
        }
        if (!StringUtils.isEmpty(resourceTagroot)) {
            properties.setProperty("RESOURCE.tagRoot", resourceTagroot);
        }
        if (!StringUtils.isEmpty(resourceTagsuffix)) {
            properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix);
        }
        if (!StringUtils.isEmpty(resourceAutoCheck)) {
            properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
        }
        return properties;
    }
}
