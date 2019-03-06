package am.bibton.shared.data.api;

import java.util.List;
import am.bibton.model.convertModel.ConvertParentModel;
import am.bibton.model.ResponseModel;
import am.bibton.model.alertModel.AlertParentModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.getTransactionList.TransactionFilterRequestModel;
import am.bibton.model.checkPassCode.CheckPassCodeModel;
import am.bibton.model.countryModel.CountryParentModel;
import am.bibton.model.createAccountModel.CreateAccountModel;
import am.bibton.model.forgetPassCodeModel.ForgetPassCodeModel;
import am.bibton.model.getTransactionList.TransactionParentModel;
import am.bibton.model.phoneNumberCodeModel.CountryCode;
import am.bibton.model.rateModel.RateParentModel;
import am.bibton.model.singUpModel.SignUp;
import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST("api/transactions/get-transactions-list")
    Flowable<ResponseModel<TransactionParentModel>> getTransactionList();

    @GET("api/wallet/get-wallet-balance-list")
    Flowable<ResponseModel<WalletCurrencyParentResponse>> getCurrencyWalletList();

    @POST("api/transactions/get-transactions-list")
    Flowable<ResponseModel<TransactionParentModel>> getFilteredListTransaction(@Body TransactionFilterRequestModel transactionFilterRequestModel);

    @FormUrlEncoded
    @POST("api/transactions/get-transactions-list")
    Flowable<ResponseModel<TransactionParentModel>> getTransactionWithCurrency(@Field("currency_id") int currency_id);

    @POST("api/transactions/get-transactions-list")
    Flowable<ResponseModel<TransactionParentModel>> getTransactionListUnfiltered();

    @FormUrlEncoded
    @POST("api/transactions/get-transactions-list")
    Flowable<ResponseModel<TransactionParentModel>> getTransactionListWithCurrency(@Field("currency_id") int currency_id);

    @FormUrlEncoded
    @POST("api/rate/exchange")
    Flowable<ResponseModel<ExchangeParentModel>> getExchangeRate(@Field("from") int exchange_from, @Field("to") int exchange_to, @Field("value") int value);

    @GET("api/rate/show/user-rate-list")
    Flowable<ResponseModel<RateParentModel>> getRateList();

    @FormUrlEncoded
    @POST("api/rate/add/user-rate")
    Flowable<ResponseModel<List>> addCurrencyPair(@Field("first_currency") int firstCurrencyID, @Field("second_currency") int secondCurrencyid);

    @GET("api/rate/currency")
    Flowable<ResponseModel<CurrencyParentModel>> getCurrencList();

    @FormUrlEncoded
    @POST("api/rate/delete/user-rate-pair")
    Flowable<ResponseModel<List>> deleteRateItem(@Field("pair_id") int pairId);

    @GET("api/rate/show/user-comparable-list?")
    Flowable<ResponseModel<ConvertParentModel>> getConvertList(@Query("amount") float amount);

    @FormUrlEncoded
    @POST("api/rate/delete/comparable-currency")
    Flowable<ResponseModel<List>> deleteConvertItem(@Field("compare_id") int compareId);

    @FormUrlEncoded
    @POST("api/rate/add/comparable-rates")
    Flowable<ResponseModel<List>> addConvertItem(@Field("currency_id") int currencyId);

    @GET("api/rate/alert/list")
    Flowable<ResponseModel<AlertParentModel>> getAlertList();

    @FormUrlEncoded
    @POST("api/rate/alert/delete")
    Flowable<ResponseModel<List>> deleteAlertItem(@Field("alert_id") int alertId);

    @FormUrlEncoded
    @POST("api/rate/comparable-make-main")
    Flowable<ResponseModel<List>> makeCurrencyMain(@Field("currency_id") int currencyId);

    @FormUrlEncoded
    @POST("api/rate/alert/change-status")
    Flowable<ResponseModel<List>> switchAlert(@Field("alert_id") int currencyId);

    @FormUrlEncoded
    @POST("api/rate/alert/add")
    Flowable<ResponseModel<List>> addAlert(@Field("from_currency") int fromCurrency, @Field("to_currency") int toCurrency, @Field("amount") float amount);

    @FormUrlEncoded
    @POST("api/user/user-wallet-exists")
    Flowable<ResponseModel<UserInfoForTransferModel>> getUserInfoForTransfer(@Field("data") int uniqueId);


}