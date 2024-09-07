# Retrofit
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Retrofit interfaces
-keep interface retrofit2.* { *; }
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}

# Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.annotations.** { *; }
-dontwarn com.google.gson.**

# Room database
-keep class androidx.room.** { *; }
-keep @androidx.room.Dao class * { *; }
-dontwarn androidx.room.**
-keep class * extends androidx.room.RoomDatabase { *; }

# Reflection untuk query Room
-keep class kotlin.Metadata { *; }

# Koin Dependency Injection
-keepclassmembers class * implements org.koin.core.module.Module { *; }
-dontwarn org.koin.**

# Paging 3
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

# Kotlin Coroutines
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.LibraryGlideModule
-keep public class * extends com.bumptech.glide.module.GlideModule
-dontwarn com.bumptech.glide.**

# Jetpack libraries
-dontwarn androidx.**
-keep class androidx.** { *; }

# DataStore Preferences
-dontwarn androidx.datastore.preferences.**
-keep class androidx.datastore.preferences.** { *; }
