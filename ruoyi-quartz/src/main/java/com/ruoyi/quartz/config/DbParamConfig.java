package com.ruoyi.quartz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.master")
public class DbParamConfig {
    private String url;
    private String username;
    private String password;

    private String databaseName;
    private String host;
    private String port;

    public void setUrl(String url) {
        this.url = url.substring(url.indexOf("://")+3, url.indexOf("?"));
        this.databaseName = this.url.split("/")[1];
        this.host = this.url.split("/")[0].split(":")[0];
        this.port = this.url.split("/")[0].split(":")[1];
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
