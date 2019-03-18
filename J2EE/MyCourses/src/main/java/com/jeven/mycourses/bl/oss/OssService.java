package com.jeven.mycourses.bl.oss;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by jeven on 2019/3/19.
 */
@Service
public class OssService {
    @Autowired
    private OSSConfig ossConfig;

    public JSONObject getSTS(){
        JSONObject jsonObject=new JSONObject();
        String endpoint = ossConfig.endpoint;
        String accessKeyId = ossConfig.accessKeyId;
        String accessKeySecret = ossConfig.accessKeySecret;
        String roleArn = ossConfig.roleArn;
        //roleSessionName时临时Token的会话名称，自己指定用于标识你的用户，或者用于区分Token颁发给谁
        //要注意roleSessionName的长度和规则，不要有空格，只能有'-'和'_'字母和数字等字符
        Random rand = new Random();
        int seed=rand.nextInt(10000);
        String roleSessionName = ""+seed;
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ProtocolType protocolType = ProtocolType.HTTPS;
        try {
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setProtocol(protocolType);
            request.setDurationSeconds(1200L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            System.out.println("=============" + response.getCredentials().getAccessKeyId());
            System.out.println("=============" + response.getCredentials().getAccessKeySecret());
            System.out.println("=============" + response.getCredentials().getSecurityToken());
            jsonObject.put("accessKeyId",response.getCredentials().getAccessKeyId());
            jsonObject.put("accessKeyIdSecret",response.getCredentials().getAccessKeySecret());
            jsonObject.put("stsToken",response.getCredentials().getSecurityToken());
            jsonObject.put("region",ossConfig.region);
            jsonObject.put("bucket",ossConfig.bucket);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
