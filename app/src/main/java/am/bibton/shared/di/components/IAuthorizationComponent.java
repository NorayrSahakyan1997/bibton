package am.bibton.shared.di.components;

import am.bibton.shared.di.components.root.IAppComponent;
import am.bibton.shared.di.modules.AuthorizationModule;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.view.activities.exchangeActivity.ExchangeActivity;
import am.bibton.view.activities.homeActivity.homeFragments.statementFragment.StatementFragment;
import am.bibton.view.activities.homeActivity.homeFragments.homeFragment.HomeFragment;
import am.bibton.view.activities.ratesActivity.addAlertActivity.AddAlertActivity;
import am.bibton.view.activities.ratesActivity.addConvertActivity.AddConvertActivity;
import am.bibton.view.activities.ratesActivity.addRateActivity.AddRateActivity;
import am.bibton.view.activities.ratesActivity.addSecondAlertPairActivity.SecondAlertPairActivity;
import am.bibton.view.activities.ratesActivity.alertFragment.AlertFragment;
import am.bibton.view.activities.ratesActivity.ratesFragments.convertFragment.ConverterFragment;
import am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment.RatesFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.forgetPassCodeFragment.ForgetPassCodeFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.countrySearchFragment.CountrySearchFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.createPassCodeFragment.CreatePassCodeFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.inputCodeFragment.InputPhoneCodeFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.phoneNumberFragment.PhoneNumberFragment;
import dagger.Component;

@AuthorizationScope
@Component(dependencies = {IAppComponent.class}, modules = {AuthorizationModule.class})
public interface IAuthorizationComponent {

    void inject(PhoneNumberFragment fragment);

    void inject(CountrySearchFragment countrySearchFragment);

    void inject(InputPhoneCodeFragment inputPhoneCodeFragment);

    void inject (CreatePassCodeFragment createPassCodeFragment);

    void inject (ForgetPassCodeFragment forgetPassCodeFragment);

    void inject(HomeFragment homeFragment);

    void inject(StatementFragment statementFragment);

    void inject(ExchangeActivity exchangeActivity);

    void inject(RatesFragment ratesFragment);

    void inject(AddRateActivity addCurrencyActivity);

    void inject(ConverterFragment converterFragment);

    void inject(AddConvertActivity addConvertActivity);

    void inject(AlertFragment alertFragment);

    void inject(AddAlertActivity addAlertActivity);

    void inject(SecondAlertPairActivity SecondAlertPairActivity);



}