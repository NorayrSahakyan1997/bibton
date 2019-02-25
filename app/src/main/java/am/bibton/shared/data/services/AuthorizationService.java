package am.bibton.shared.data.services;

import java.util.List;

import am.bibton.model.ResponseModel;
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
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.shared.data.api.IAuthorizationService;
import am.bibton.shared.data.services.root.BaseService;
import io.reactivex.Flowable;

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
        return request(mService.checkPassCode(unique_id, code));
    }

    public Flowable<ResponseModel<SignUp>> checkValidationPhoneNumber(String shortName, String phoneNumber) {
        return request(mService.checkPhoneNumberValidation(shortName, phoneNumber));
    }

    public Flowable<ResponseModel<CreateAccountModel>> createAccount(String unique_id, String code, String passcode) {
        return request(mService.createAccount(unique_id, code, passcode));
    }

    public Flowable<ResponseModel<ForgetPassCodeModel>> forgetPass(String unique_id, String passCode) {
        return request(mService.forgetPassCode(unique_id, passCode));
    }

    public Flowable<ResponseModel<TransactionParentModel>> getTransaction() {
        return request(mService.getTransactionList());
    }


    public Flowable<ResponseModel<WalletCurrencyParentResponse>> getCurrency() {
        return request(mService.getCurrencyWalletList());
    }


    public Flowable<ResponseModel<TransactionParentModel>> getTransactionFiltered(TransactionFilterRequestModel transactionFilterRequestModel) {
        return request(mService.getFilteredListTransaction(transactionFilterRequestModel));
    }

    public Flowable<ResponseModel<TransactionParentModel>> getTransactionsUnfiltered() {
        return request(mService.getTransactionListUnfiltered());
    }

    public Flowable<ResponseModel<TransactionParentModel>> getTransactionWithCurrency(int from_currency) {
        return request(mService.getTransactionWithCurrency(from_currency));
    }

    public Flowable<ResponseModel<ExchangeParentModel>> changeMoney(int from_currency, int to_currency, int amount) {
        return request(mService.getExchangeRate(from_currency, to_currency, amount));
    }

    public Flowable<ResponseModel<RateParentModel>> getRateList() {
        return request(mService.getRateList());
    }

    public Flowable<ResponseModel<List>> addCurrencyPair(int firstCurrencyId, int secondCurrencyId) {
        return request(mService.addCurrencyPair(firstCurrencyId, secondCurrencyId));
    }
    public Flowable<ResponseModel<CurrencyParentModel>> getCurrencyList() {
        return request(mService.getCurrencList());
    }

    public Flowable<ResponseModel<List>> deleteRateItem(int pairID){
        return request(mService.deleteRateItem(pairID));
    }

}