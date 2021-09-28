package za.co.sanlam.nexus.nexus_monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.sanlam.nexus.nexus_monitor.model.NexusStorageStats;
import za.co.sanlam.nexus.nexus_monitor.service.NexusScriptService;

import java.util.List;

@Controller
public class NexusController {

    @Autowired
    private NexusScriptService nexusScriptService;

    public ResponseEntity<List<NexusStorageStats>>retrieveNexusStorageStats(){

        ResponseEntity<String> scriptExistResponse = nexusScriptService.isScriptExist();
        if(scriptExistResponse.getStatusCode()== HttpStatus.OK){



        }
        return null;
    }

}
