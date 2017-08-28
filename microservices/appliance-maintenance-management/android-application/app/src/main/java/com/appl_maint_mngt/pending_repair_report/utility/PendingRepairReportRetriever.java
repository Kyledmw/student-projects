package com.appl_maint_mngt.pending_repair_report.utility;

import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportReadableRepository;
import com.appl_maint_mngt.pending_repair_report.utility.interfaces.IPendingRepairReportRetriever;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportListView;

import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public class PendingRepairReportRetriever implements IPendingRepairReportRetriever {

    @Override
    public List<IPendingRepairReportReadable> retrievePending() {
        return IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository().getAllForStatus(ResponseStatus.PENDING);
    }

    @Override
    public List<IPendingRepairReportReadable> retrievePendingAndDeclined() {
        IPendingRepairReportReadableRepository repository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository();
        List<IPendingRepairReportReadable> list = repository.getAllForStatus(ResponseStatus.PENDING);
        list.addAll(repository.getAllForStatus(ResponseStatus.DECLINED));
        return list;
    }
}
