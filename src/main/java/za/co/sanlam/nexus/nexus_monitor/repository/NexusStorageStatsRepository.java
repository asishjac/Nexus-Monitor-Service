package za.co.sanlam.nexus.nexus_monitor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import za.co.sanlam.nexus.nexus_monitor.model.NexusStorageStats;

import java.util.List;

@Repository
public interface NexusStorageStatsRepository extends MongoRepository<NexusStorageStats,String> {


}
