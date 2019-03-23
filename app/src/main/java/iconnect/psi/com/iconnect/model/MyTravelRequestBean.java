package iconnect.psi.com.iconnect.model;

import com.google.gson.annotations.SerializedName;

public class MyTravelRequestBean {
    @SerializedName("emp_code")
    private String emp_code;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("name")
    private String name;

    @SerializedName("same_day")
    private String same_day;

    @SerializedName("start_location")
    private String start_location;

    @SerializedName("end_location")
    private String end_location;

    @SerializedName("travel_type")
    private String travel_type;

    @SerializedName("start_date")
    private String start_date;

    @SerializedName("end_date")
    private String end_date;

    @SerializedName("leaving_HQ")
    private String leaving_HQ;

    @SerializedName("reaching_HQ")
    private String reaching_HQ;

    @SerializedName("total_night")
    private String total_night;

    @SerializedName("superior_comment")
    private String superior_comment;

    @SerializedName("submit_status")
    private String submit_status;

    @SerializedName("approval_status")
    private String approval_status;

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSame_day() {
        return same_day;
    }

    public void setSame_day(String same_day) {
        this.same_day = same_day;
    }

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }

    public String getTravel_type() {
        return travel_type;
    }

    public void setTravel_type(String travel_type) {
        this.travel_type = travel_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getLeaving_HQ() {
        return leaving_HQ;
    }

    public void setLeaving_HQ(String leaving_HQ) {
        this.leaving_HQ = leaving_HQ;
    }

    public String getReaching_HQ() {
        return reaching_HQ;
    }

    public void setReaching_HQ(String reaching_HQ) {
        this.reaching_HQ = reaching_HQ;
    }

    public String getTotal_night() {
        return total_night;
    }

    public void setTotal_night(String total_night) {
        this.total_night = total_night;
    }

    public String getSuperior_comment() {
        return superior_comment;
    }

    public void setSuperior_comment(String superior_comment) {
        this.superior_comment = superior_comment;
    }

    public String getSubmit_status() {
        return submit_status;
    }

    public void setSubmit_status(String submit_status) {
        this.submit_status = submit_status;
    }

    public String getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }
}
