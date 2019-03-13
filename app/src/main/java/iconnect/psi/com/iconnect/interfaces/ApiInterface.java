package iconnect.psi.com.iconnect.interfaces;

import java.util.HashMap;

import iconnect.psi.com.iconnect.model.LoginResponse;
import iconnect.psi.com.iconnect.model.ProjectResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("userLogin")
    public Call<LoginResponse> sendLoginData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("projectList")
    public Call<ProjectResponse> sendProjectData(@FieldMap HashMap<String, String> hashMap);
}
