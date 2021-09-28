package za.co.sanlam.nexus.nexus_monitor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryStats {

    private String repositoryName;

    private String totalBytes;

    private String reclaimableBytes;
}
