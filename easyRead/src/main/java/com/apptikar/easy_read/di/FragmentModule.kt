package com.apptikar.easy_read.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.concurrent.Executors


@Module
@InstallIn(FragmentComponent::class)
object FragmentModule{

//   @Provides
//  fun imageAnalyzerProvider(@ActivityContext context: Context) = ImageAnalyzer(context = context)

    @Provides
    fun executorProvider() = Executors.newSingleThreadExecutor()

//    @Provides
//    fun cameraProvider(@ActivityContext context: Context) = ProcessCameraProvider.getInstance(context)
//
//    @Provides
//    fun cameraSelector() = CameraSelector.Builder()
//    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//    .build()
//
//    @Provides
//    fun imageAnalysisBuilder() = ImageAnalysis.Builder()
//        .setTargetResolution(Size(1280, 720))
//        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//        .build()
//
//    @Provides
//    fun previewProvider () = Preview.Builder().build()

//    @Provides
//    fun mediaPlayer(@ActivityContext context: Context) = MediaPlayer.create(context, R.raw.sound_effect)

}