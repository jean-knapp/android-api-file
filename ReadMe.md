An android library to access the Android File System.

## Setup

If you're using Maven, then edit your project's "pom.xml" and add this to the `<dependencies>` section:

```xml
<dependency>
    <groupId>com.github.jean-knapp</groupId>
    <artifactId>android-api-file</artifactId>
    <version>1.0.1</version>
</dependency>
```

If you are using Gradle, then edit your project's "build.gradle" and add this to the `dependencies` section:

```groovy
dependencies {
    // ...
    implementation 'com.github.jean-knapp:android-api-file:1.0.1'
}
```

You can also download the Android AAR and and its required dependencies directly from the [latest release page](https://github.com/jean-knapp/android-api-file/releases/latest). Note that the distribution artifacts on the releases pages do not contain optional dependencies.

## Settings

In order to access the external storage, add this to the app's "AndroidManifest.xml":
```   
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

In order to download files from the internet, add this to the app's "AndroidManifest.xml":
```
<uses-permission android:name="android.permission.INTERNET" />
```
