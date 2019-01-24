package am.spaysapps.bibton.shared.data.services;

import java.util.List;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import am.spaysapps.bibton.shared.data.api.IAuthorizationService;
import am.spaysapps.bibton.shared.data.services.root.BaseService;
import io.reactivex.Flowable;

public class AuthorizationService extends BaseService {

    private final IAuthorizationService mService;

    public AuthorizationService(IAuthorizationService service) {
        mService = service;
    }

    public Flowable<ResponseModel<CountryParentModel>> signIn() {
        return request(mService.signIn());
    }
}