package com.cindymb.airportapplication.di;

import com.cindymb.airportapplication.AirportApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        RepositoryModule.class,
        FragmentModule.class,
        ViewModelModule.class,
})

public interface AppComponent {

    void inject(AirportApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(AirportApplication application);

        Builder repoModule(RepositoryModule aRepositoryModule);

        Builder appModule(AppModule appModule);

        AppComponent build();
    }

}
