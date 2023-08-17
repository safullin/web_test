package com.example.web_test

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

public class MainActivity : FlutterActivity() {
    private val  CHANNEL:String = "native_channel";

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "sendStringToNative") {
                val barcode = call.argument<String>("message")
                barcode?.let{sendKeyEvents(it)}
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
    }
    private fun sendKeyEvents(barcode:String){
        Log.e("TEST", barcode+"sdfsdlfklsldkfjsdlkf")
    }
}
