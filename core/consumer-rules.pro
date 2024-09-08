-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep interface retrofit2.* { *; }
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keepattributes Signature, *Annotation*

-keep class androidx.room.** { *; }
-keep @androidx.room.Dao class * { *; }
-dontwarn androidx.room.**
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

-keep class kotlin.Metadata { *; }
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }
-keepclassmembers class * implements org.koin.core.module.Module { *; }
-dontwarn org.koin.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.LibraryGlideModule
-dontwarn com.bumptech.glide.**

-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn com.android.org.conscrypt.SSLParametersImpl
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

-dontwarn com.submission.valorantagentandroid.core.**
-keep class com.submission.valorantagentandroid.core.** { *; }

-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
    *** rewind();
}

-ignorewarnings
-keep class * {
    public private *;
}

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-dontwarn org.xmlpull.v1.**
-dontnote org.xmlpull.v1.**
-keep class org.xmlpull.** { *; }

-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
