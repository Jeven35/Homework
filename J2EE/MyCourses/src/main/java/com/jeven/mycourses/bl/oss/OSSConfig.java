package com.jeven.mycourses.bl.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: jeven
 * @Description:
 * @Modifiedby:
 */
@Component
public class OSSConfig {
    @Value("${oss.endpoint}")
    public String endpoint;
    @Value("${oss.accessKeyId}")
    public String accessKeyId ;
    @Value("${oss.accessKeySecret}")
    public String accessKeySecret ;
    @Value("${oss.roleArn}")
    public String roleArn;
    @Value("${oss.bucket}")
    public String bucket;
    @Value("${oss.region}")
    public String region;
}
