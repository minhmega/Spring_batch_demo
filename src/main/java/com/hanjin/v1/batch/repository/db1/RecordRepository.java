package com.hanjin.v1.batch.repository.db1;

import com.hanjin.v1.batch.entity.db1.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
