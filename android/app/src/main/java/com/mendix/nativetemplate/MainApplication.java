package com.mendix.nativetemplate;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.react.PackageList;
import com.facebook.react.ReactPackage;
import com.mendix.mendixnative.MendixReactApplication;
import com.mendix.mendixnative.react.splash.MendixSplashScreenPresenter;
import com.microsoft.codepush.react.CodePush;

import org.devio.rn.splashscreen.SplashScreen;

import java.util.Arrays;
import java.util.List;

import io.invertase.firebase.RNFirebasePackage;
import io.invertase.firebase.messaging.RNFirebaseMessagingPackage;
import io.invertase.firebase.notifications.RNFirebaseNotificationsPackage;

public class MainApplication extends MendixReactApplication {
    @Override
    public boolean getUseDeveloperSupport() {
        return false;
    }

    @Override
    public List<ReactPackage> getPackages() {
        List<ReactPackage> packages = new PackageList(this).getPackages();

        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        packages.add(new CodePush(getCodePushKey(), getApplicationContext(), BuildConfig.DEBUG));

        if (BuildConfig.USE_FIREBASE) {
            packages.addAll(Arrays.asList(
                    new RNFirebaseMessagingPackage(),
                    new RNFirebaseNotificationsPackage(),
                    new RNFirebasePackage()
            ));
        }

        return packages;
    }

    @Override
    public MendixSplashScreenPresenter createSplashScreenPresenter() {
        return new MendixSplashScreenPresenter() {
            @Override
            public void show(@NonNull Activity activity) {
                hide(activity);
                SplashScreen.show(activity, true);
            }

            @Override
            public void hide(@NonNull Activity activity) {
                SplashScreen.hide(activity);
            }
        };
    }
}
