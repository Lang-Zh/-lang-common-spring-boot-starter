package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lang.oss.hello")
public class OssHelloProperties extends OssProperties{

    private String loginSubject;

    private String password;

    public String getLoginSubject() {
        return loginSubject;
    }

    public void setLoginSubject(String loginSubject) {
        this.loginSubject = loginSubject;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
