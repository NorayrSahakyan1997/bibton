package am.spaysapps.bibton.shared.data.services;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.checkPassCode.CheckPassCodeModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import am.spaysapps.bibton.model.phoneNumberCodeModel.CountryCode;
import am.spaysapps.bibton.model.singUpModel.SignUp;
import am.spaysapps.bibton.shared.data.api.IAuthorizationService;
import am.spaysapps.bibton.shared.data.services.root.BaseService;
import io.reactivex.Flowable;
import okhttp3.Response;

public class AuthorizationService extends BaseService {

    private final IAuthorizationService mService;

    public AuthorizationService(IAuthorizationService service) {
        mService = service;
    }

    public Flowable<ResponseModel<CountryParentModel>> getCountryList() {
        return request(mService.getCountryList());
    }

    public Flowable<ResponseModel<CountryCode>> getCountryCode() {
        return request(mService.getCountryCode());
    }

    public Flowable<ResponseModel<SignUp>> getUserInfo(String shortName, String phoneNumber) {
        return request(mService.getUserInfo(shortName, phoneNumber));
    }


    public Flowable<ResponseModel<CheckPassCodeModel>> checkPassCode(String unique_id, String code) {
        return request(mService.checkPassCode(unique_id,code));
    }

    public Flowable<ResponseModel<SignUp>> checkValidationPhoneNumber(String shortName, String phoneNumber) {
        return request(mService.checkPhoneNumberValidation(shortName,phoneNumber));
    }

}