package pl.dabal.selfstorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dabal.selfstorage.model.Metric;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}