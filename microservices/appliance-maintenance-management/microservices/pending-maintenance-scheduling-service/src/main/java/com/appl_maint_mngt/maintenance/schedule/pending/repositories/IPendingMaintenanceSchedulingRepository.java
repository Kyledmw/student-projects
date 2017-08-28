package com.appl_maint_mngt.maintenance.schedule.pending.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;

@Repository
public interface IPendingMaintenanceSchedulingRepository extends JpaRepository<PendingMaintenanceSchedule, Long> {

	List<PendingMaintenanceSchedule> findByRepairReportId(Long repairReportId);
}
