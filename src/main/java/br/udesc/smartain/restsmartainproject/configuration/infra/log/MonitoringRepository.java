package br.udesc.smartain.restsmartainproject.configuration.infra.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {
}
