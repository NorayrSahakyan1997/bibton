package am.spaysapps.bibton.shared.data.api;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.checkPassCode.CheckPassCodeModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import am.spaysapps.bibton.model.createAccountModel.CreateAccountModel;
import am.spaysapps.bibton.model.forgetPassCodeModel.ForgetPassCodeModel;
import am.spaysapps.bibton.model.phoneNumberCodeModel.CountryCode;
import am.spaysapps.bibton.model.singUpModel.SignUp;
import am.spaysapps.bibton.view.activities.welcomeActivity.welcomeFragments.forgetPassCodeFragment.ForgetPassCodeFragment;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAuthorizationService {

    @GET("api/country-mobile")
    Flowable<ResponseModel<CountryParentModel>> getCountryList();

    @POST("api/country-phone-code")
    Flowable<ResponseModel<CountryCode>> getCountryCode();

    @FormUrlEncoded
    @POST("api/login")
    Flowable<ResponseModel<SignUp>> getUserInfo(@Field("shortname") String shortName, @Field("phone_number") String phoneNumber);

    @FormUrlEncoded
    @POST("api/login/second-step")
    Flowable<ResponseModel<CheckPassCodeModel>> checkPassCode(@Field("unique_id") String unique_id, @Field("code") String code);

    @FormUrlEncoded
    @POST("api/login")
    Flowable<ResponseModel<SignUp>> checkPhoneNumberValidation(@Field("shortname") String shortName, @Field("phone_number") String phoneNumber);

    @FormUrlEncoded
    @POST("api/login/third-step")
    Flowable<ResponseModel<CreateAccountModel>> createAccount(@Field("unique_id") String unique_id, @Field("code") String code, @Field("passcode") String passCode);

    @FormUrlEncoded
    @POST("api/recover/new-passcode")
    Flowable<ResponseModel<ForgetPassCodeModel>> forgetPassCode(@Field("unique_id") String unique_id, @Field("passcode") String passCode);






}