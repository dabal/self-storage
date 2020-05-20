package pl.dabal.selfstorage.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.model.Metric;
import pl.dabal.selfstorage.repository.MetricRepository;
import pl.dabal.selfstorage.service.MetricService;


import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MetricServiceImpl implements MetricService {
    private MetricRepository metricRepository;

    public List<Metric> metricList() {
        return metricRepository.findAll();
    }
}
