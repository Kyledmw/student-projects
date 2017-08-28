package com.appl_maint_mngt.diagnostic_request.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_request.models.DiagnosticRequest;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DummyDiagnosticRequestService implements IDiagnosticRequestService {

    @Override
    public void post(IDiagnosticRequestPayload payload, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        DiagnosticRequest diagnosticRequest = new DiagnosticRequest();
        diagnosticRequest.setId((long) 1);
        diagnosticRequest.setDiagnosticReportId(payload.getDiagnosticReportId());
        diagnosticRequest.setMaintenanceOrganisationId(payload.getMaintenanceOrganisationId());
        diagnosticRequest.setResponseStatus(ResponseStatus.PENDING);

        repository.addItem(diagnosticRequest);
    }

    @Override
    public void findByDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        List<DiagnosticRequest> diagnosticRequestList = new ArrayList<>();

        DiagnosticRequest diagnosticRequest1 = new DiagnosticRequest();
        diagnosticRequest1.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest1.setId((long) 1);
        diagnosticRequest1.setMaintenanceOrganisationId((long) 1);
        diagnosticRequest1.setDiagnosticReportId(diagRepId);
        diagnosticRequestList.add(diagnosticRequest1);

        repository.addItems(diagnosticRequestList);

    }

    @Override
    public void findByDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        List<DiagnosticRequest> diagnosticRequestList = new ArrayList<>();
        long counter = 1;
        for(Long id: diagRepIds) {
            DiagnosticRequest req = new DiagnosticRequest();
            req.setDiagnosticReportId(id);
            req.setMaintenanceOrganisationId((long) 1);
            req.setId(counter);
            req.setResponseStatus(ResponseStatus.PENDING);
            counter++;
            diagnosticRequestList.add(req);
        }

        repository.addItems(diagnosticRequestList);
    }

    @Override
    public void findByMaintenanceOrganisationId(Long maintOrgId, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        List<DiagnosticRequest> diagnosticRequestList = new ArrayList<>();

        DiagnosticRequest diagnosticRequest1 = new DiagnosticRequest();
        diagnosticRequest1.setResponseStatus(ResponseStatus.PENDING);
        diagnosticRequest1.setId((long) 1);
        diagnosticRequest1.setMaintenanceOrganisationId(maintOrgId);
        diagnosticRequest1.setDiagnosticReportId((long) 1);
        diagnosticRequestList.add(diagnosticRequest1);

        repository.addItems(diagnosticRequestList);
    }

    @Override
    public void put(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback) {
        IDiagnosticRequestUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();

        DiagnosticRequest diagnosticRequest = new DiagnosticRequest();
        diagnosticRequest.setId(payload.getId());
        diagnosticRequest.setDiagnosticReportId(payload.getDiagnosticReportId());
        diagnosticRequest.setMaintenanceOrganisationId(payload.getMaintenanceOrganisationId());
        diagnosticRequest.setResponseStatus(payload.getResponseStatus());

        repository.addItem(diagnosticRequest);
    }
}
