package za.co.sanlam.nexus.nexus_monitor.service;


import org.springframework.http.ResponseEntity;
import za.co.sanlam.nexus.nexus_monitor.dto.BlobStatistics;

import java.util.HashMap;

public interface NexusScriptService {

     String addNexusScript();
     ResponseEntity<String> isScriptExist();
     HashMap<String, BlobStatistics> runNexusScript();

}
