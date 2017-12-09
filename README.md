# AutoInjector
export AppInjector of https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample


# Setup
allprojects {
    repositories {
        ....
        maven { url "https://jitpack.io" }
    }
}

implementation 'com.github.ppwasin:autoinjector:1.0.0'


# Sample Usage
AutoExecutor.init(app)  # app is Application instance


# Cautions
must use AutoExecutor.clear() in @After of test for removing lifecycle registering

