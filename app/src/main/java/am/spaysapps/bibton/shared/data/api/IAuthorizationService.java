package am.spaysapps.bibton.shared.data.api;

import java.util.List;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import io.reactivex.Flowable;
import retrofit2.http.POST;

public interface IAuthorizationService {

    @POST("api/country-mobile")
    Flowable<ResponseModel<CountryParentModel>> signIn();
}