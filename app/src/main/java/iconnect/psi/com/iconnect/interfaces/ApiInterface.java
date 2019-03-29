package iconnect.psi.com.iconnect.interfaces;

import java.util.HashMap;

import iconnect.psi.com.iconnect.model.ApprovedList;
import iconnect.psi.com.iconnect.model.BookingList;
import iconnect.psi.com.iconnect.model.BookingResponse;
import iconnect.psi.com.iconnect.model.CityResponse;
import iconnect.psi.com.iconnect.model.CompleteList;
import iconnect.psi.com.iconnect.model.ItinearyResponse;
import iconnect.psi.com.iconnect.model.LoginResponse;
import iconnect.psi.com.iconnect.model.Perdiam;
import iconnect.psi.com.iconnect.model.ProjectResponse;
import iconnect.psi.com.iconnect.model.PurposeResponse;
import iconnect.psi.com.iconnect.model.RejectList;
import iconnect.psi.com.iconnect.model.TravelBooking;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("userLogin")
    public Call<LoginResponse> sendLoginData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("projectList")
    public Call<ProjectResponse> sendProjectData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("empBooking")
    public Call<BookingList> showBookingData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("approvedBooking")
    public Call<ApprovedList> showApprovedData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("rejectBooking")
    public Call<RejectList> showRejectData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("completeBooking")
    public Call<CompleteList> showCompleteData(@FieldMap HashMap<String, String> hashMap);
   // @FormUrlEncoded
   /* @POST("completeBooking")
    public Call<MyTravelRequestBean> sendTravelRequest(@FieldMap HashMap<String, String> hashMap);*/

    @FormUrlEncoded
    @POST("completeBooking")
    public Call<BookingResponse> sendBookingResponse(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("airportList")
    public Call<CityResponse> sendCityResponse(@FieldMap HashMap<String, String> hashMap);

   @Multipart
    @POST("purpose")
   Call<PurposeResponse>sendPurposeResponse(@Part("API_KEY") RequestBody apiKey, @Part("purpose") RequestBody purpose, @Part("attach_doc") RequestBody attachImage);
    //public Call<PurposeResponse> sendPurposeResponse(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("itenary")
    public Call<ItinearyResponse> sendItinearyResponse(@FieldMap HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("travelBooking")
    public Call<TravelBooking> sendTravelBookingResponse(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("perDiam")
    public Call<Perdiam> perDiam(@FieldMap HashMap<String, String> hashMap);



}
