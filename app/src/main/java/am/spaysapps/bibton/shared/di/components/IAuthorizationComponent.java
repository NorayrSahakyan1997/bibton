package am.spaysapps.bibton.shared.di.components;

import am.spaysapps.bibton.shared.di.components.root.IAppComponent;
import am.spaysapps.bibton.shared.di.modules.AuthorizationModule;
import am.spaysapps.bibton.shared.di.scopes.AuthorizationScope;
import am.spaysapps.bibton.view.fragments.PhoneNumberFragment;
import dagger.Component;

@AuthorizationScope
@Component(dependencies = {IAppComponent.class}, modules = {AuthorizationModule.class})
public interface IAuthorizationComponent {
    void inject(PhoneNumberFragment fragment);
}