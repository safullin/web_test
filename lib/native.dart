import 'package:flutter/services.dart';

const _platformChannel = MethodChannel('com.example.web_test/native_channel');

class NativeChannel {
  static Future<void> sendStringToNative(String message) async {
    try {
      await _platformChannel
          .invokeMethod('sendStringToNative', {'message': message});
    } catch (e) {
      print('Error sending string to native: $e');
    }
  }
}
