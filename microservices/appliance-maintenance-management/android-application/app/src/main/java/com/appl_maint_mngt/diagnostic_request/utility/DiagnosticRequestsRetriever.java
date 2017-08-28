package com.appl_maint_mngt.diagnostic_request.utility;

import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.interfaces.IDiagnosticRequestsRetriever;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestsRetriever implements IDiagnosticRequestsRetriever {
    private static final Logger logger = LoggerManager.getLogger(DiagnosticRequestsRetriever.class);

    @Override
    public List<IDiagnosticRequestReadable> getPendingAndResponded() {
        List<IDiagnosticRequestReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getAll();
        logger.i("DiagnosticRequestRetriever: getPendingAndResponded(): list size: %d", list.size());

        List<IDiagnosticRequestReadable> diagReqs = new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.PENDING);
        diagReqs.addAll(new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.RESPONDED));

        logger.i("DiagnosticRequestRetriever: getPendingAndResponded(): filtered size: %d", diagReqs.size());

        return diagReqs;
    }

    @Override
    public List<IDiagnosticRequestReadable> getPending() {
        List<IDiagnosticRequestReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getAll();
        List<IDiagnosticRequestReadable> diagReqs = new DiagnosticRequestListFilter().filterStatus(list, ResponseStatus.PENDING);

        return diagReqs;
    }
}
