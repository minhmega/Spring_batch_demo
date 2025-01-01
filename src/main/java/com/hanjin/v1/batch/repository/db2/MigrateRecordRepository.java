package com.hanjin.v1.batch.repository.db2;

import com.hanjin.v1.batch.entity.db1.Record;
import com.hanjin.v1.batch.entity.db2.MigrateRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MigrateRecordRepository extends JpaRepository<MigrateRecord, Long> {
}
