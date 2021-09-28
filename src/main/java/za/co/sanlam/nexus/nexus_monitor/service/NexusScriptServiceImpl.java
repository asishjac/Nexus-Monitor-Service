package za.co.sanlam.nexus.nexus_monitor.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.sanlam.nexus.nexus_monitor.config.ApplicationConfig;
import za.co.sanlam.nexus.nexus_monitor.dto.BlobStatistics;
import za.co.sanlam.nexus.nexus_monitor.utils.NexusGroovyScriptUtil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;

@Service
public class NexusScriptServiceImpl implements NexusScriptService {

@Autowired
private NexusGroovyScriptUtil nexusGroovyScriptUtil;

@Autowired
private ApplicationConfig applicationConfig;

    public String addNexusScript(){
        String nexusScriptUrl=applicationConfig.nexusUrl+applicationConfig.nexusRestApiUrl+
                applicationConfig.nexusRestApiScriptUrl;
        File nexusScriptFileObject = new File(applicationConfig.nexusStorageScriptLocation+
                applicationConfig.nexusStorageScriptName);
        String scriptContent= nexusGroovyScriptUtil.readGroovyScriptFile(nexusScriptFileObject);
        JSONObject scriptJsonObject=new JSONObject();
        scriptJsonObject.put("name",applicationConfig.nexusStorageScriptName);
        scriptJsonObject.put("content",scriptContent);
        scriptJsonObject.put("type",applicationConfig.nexusStorageScriptType);
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBasicAuth(applicationConfig.nexusUsername,applicationConfig.nexusPassword);
        HttpEntity<String>request=new HttpEntity<String>(scriptJsonObject.toString(),header);
        RestTemplate restTemplate = new RestTemplate();
        String scriptAddResult = restTemplate.postForObject(nexusScriptUrl,request,String.class);
        return scriptAddResult;
    }

    public ResponseEntity<String> isScriptExist(){
        String nexusScriptUrl=applicationConfig.nexusUrl+applicationConfig.nexusRestApiUrl+
                applicationConfig.nexusRestApiScriptUrl;
        String scriptName=applicationConfig.nexusStorageScriptName;
        try {
            URI nexusScriptUrlFinal = new URI(nexusScriptUrl + scriptName);
            HttpHeaders header=new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            header.setBasicAuth(applicationConfig.nexusUsername,applicationConfig.nexusPassword);
            header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<String>(header);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response=restTemplate.exchange(nexusScriptUrlFinal,HttpMethod.GET,request,String.class);
            return response;
        }catch (URISyntaxException uriSyntaxException){
            uriSyntaxException.printStackTrace();

        }
;
        return null;
    }


    public HashMap<String, BlobStatistics> runNexusScript(){
        String nexusScriptUrl=applicationConfig.nexusUrl+applicationConfig.nexusRestApiUrl+
                applicationConfig.nexusRestApiScriptUrl;
        String scriptName=applicationConfig.nexusStorageScriptName;
        String functionName=applicationConfig.nexusRestApiScriptRunUrl;
        try{
            URI nexusScriptUrlFinal = new URI(nexusScriptUrl + scriptName + functionName);
            HttpHeaders header = new HttpHeaders();
            header.setBasicAuth(applicationConfig.nexusUsername,applicationConfig.nexusPassword);
            header.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String>request=new HttpEntity<>(header);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String>response=restTemplate.exchange(nexusScriptUrlFinal,HttpMethod.POST,request,String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            HashMap<String, BlobStatistics>blobStatisticsMap=new HashMap<>();
            blobStatisticsMap=objectMapper.readValue(jsonObject.get("result").toString()
                    ,objectMapper.getTypeFactory().constructMapType(HashMap.class,String.class,BlobStatistics.class));
            return blobStatisticsMap;

        }catch(URISyntaxException uriSyntaxException){
            uriSyntaxException.printStackTrace();
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }

}
