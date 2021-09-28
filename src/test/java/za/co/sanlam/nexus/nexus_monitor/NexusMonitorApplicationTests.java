package za.co.sanlam.nexus.nexus_monitor;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import za.co.sanlam.nexus.nexus_monitor.assemble.Assemble;
import za.co.sanlam.nexus.nexus_monitor.dto.BlobStatistics;
import za.co.sanlam.nexus.nexus_monitor.model.NexusStorageStats;
import za.co.sanlam.nexus.nexus_monitor.repository.NexusStorageStatsRepository;
import za.co.sanlam.nexus.nexus_monitor.service.NexusScriptService;
import za.co.sanlam.nexus.nexus_monitor.utils.NexusGroovyScriptUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
class NexusMonitorApplicationTests {

	@Autowired
	NexusScriptService nexusScriptService;

	@Autowired
	NexusGroovyScriptUtil nexusGroovyScriptUtil;

	@Autowired
	NexusStorageStatsRepository nexusStorageStatsRepository;

	@Autowired
	NexusStorageStats nexusStorageStats;

	@Autowired
	Assemble assemble;

	@Test
	void addNexusScriptTest(){
		String result=nexusScriptService.addNexusScript();
		System.out.println(result);
	}

	@Test
	void isNexusScriptExistTest(){
		ResponseEntity<String> response = nexusScriptService.isScriptExist();
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());

	}

	@Test
	void runNexusScriptTest(){
		HashMap<String, BlobStatistics>blobStatisticsMap = nexusScriptService.runNexusScript();
		blobStatisticsMap.forEach((k,v) -> System.out.println(k + ":"+v.getAvailableSpace()));

	}

	@Test
	void readGroovyScriptFileTest(){

		File scriptFileObject = new File("C:\\monitor-apis\\nexus_monitor\\nexus_monitor\\src\\main\\resources\\groovy\\NexusMonitorService.groovy");
		String data = nexusGroovyScriptUtil.readGroovyScriptFile(scriptFileObject);
		System.out.println(data);
	}

	@Test
	void saveNexusStorageStats(){

		HashMap<String, BlobStatistics>blobStatisticsMap = nexusScriptService.runNexusScript();

		nexusStorageStatsRepository.save(assemble.assembleNexusStorageStats(blobStatisticsMap));
	}

	@Test
	void testAssembleNexusStorageStats(){
		HashMap<String, BlobStatistics>blobStatisticsMap = nexusScriptService.runNexusScript();
		nexusStorageStats = assemble.assembleNexusStorageStats(blobStatisticsMap);
	}

	@Test
	void testRetrieveBasedOnDate(){


		List<NexusStorageStats> nexusStorageStatsList = nexusStorageStatsRepository.findAll(Sort.by(Sort.Direction.DESC,
				"dateTime"));
		System.out.print("Success");
	}



	@Test
	void contextLoads() {
	}

}
