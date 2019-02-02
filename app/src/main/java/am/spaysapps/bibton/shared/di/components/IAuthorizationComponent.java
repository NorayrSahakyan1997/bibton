package am.spaysapps.bibton.shared.di.components;

import am.spaysapps.bibton.shared.di.components.root.IAppComponent;
import am.spaysapps.bibton.shared.di.modules.AuthorizationModule;
import am.spaysapps.bibton.shared.di.scopes.AuthorizationScope;
import am.spaysapps.bibton.view.fragments.countrySearchFragment.CountrySearchFragment;
import am.spaysapps.bibton.view.fragments.createPassCodeFragment.CreatePassCodeFragment;
import am.spaysapps.bibton.view.fragments.inputCodeFragment.InputPhoneCodeFragment;
import am.spaysapps.bibton.view.fragments.phoneNumberFragment.PhoneNumberFragment;
import dagger.Component;

@AuthorizationScope
@Component(dependencies = {IAppComponent.class}, modules = {AuthorizationModule.class})
public interface IAuthorizationComponent {
    void inject(PhoneNumberFragment fragment);

    void inject(CountrySearchFragment countrySearchFragment);

    void inject(InputPhoneCodeFragment inputPhoneCodeFragment);

    void inject (CreatePassCodeFragment createPassCodeFragment);

}