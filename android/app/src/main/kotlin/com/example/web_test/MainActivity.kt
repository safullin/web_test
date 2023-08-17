package com.example.web_test

import android.util.Log
import android.view.KeyCharacterMap
import android.view.KeyEvent
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
        val szRes = barcode.toCharArray()
        var encodeszRes: String? = null
        var events: Array<KeyEvent?>
        encodeszRes = URLEncoder.encode(String(szRes), StandardCharsets.UTF_8.name())
        val CharMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD)
        events = CharMap.getEvents(encodeszRes.toCharArray())
        for (event in events) activity.dispatchKeyEvent(event)
        Log.e("TEST", barcode+"  send")
    }
}
