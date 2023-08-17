package com.example.web_test

import io.flutter.embedding.android.FlutterActivity

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "com.example.web_test/native_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState,MainActivity mainActivity) {
        super.onCreate(savedInstanceState);

        new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL)
            .setMethodCallHandler((call, result) -> {
                if (call.method.equals("sendStringToNative")) {
                    String message = call.argument("message");


                    




                    // result.success(null); 
                } else {
                    result.notImplemented();
                }
            });
    }
}
