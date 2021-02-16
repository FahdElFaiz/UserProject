package com.elfaiz.examen2020.retrofit;

import com.elfaiz.examen2020.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUser {
    @GET("users?fbclid=IwAR1ILHtnRLJKb6luwme2jy2Rqwup8MW-E6CJsRDxyEV0NOydMWiVdTeCbg4")
    Call<List<User>> getListUser();
}
