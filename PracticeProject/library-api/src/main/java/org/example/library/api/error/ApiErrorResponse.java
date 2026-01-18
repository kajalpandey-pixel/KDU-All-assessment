package org.example.library.api.error;

import java.time.Instant;
import java.util.List;

public class ApiErrorResponse {
    private Instant timestamp;
    private String path;
    private String errorCode;
    private String message;
    private List<ApiErrorDetail> details;
    private String correlationId;

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<ApiErrorDetail> getDetails() { return details; }
    public void setDetails(List<ApiErrorDetail> details) { this.details = details; }

    public String getCorrelationId() { return correlationId; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
}
