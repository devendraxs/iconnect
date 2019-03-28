package iconnect.psi.com.iconnect.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurposeResponse {
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("Message")
    @Expose
    private String message;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
