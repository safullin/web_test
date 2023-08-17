import 'package:flutter/services.dart';



class NativeChannel {
  static Future<void> sendStringToNative(String message) async {
    const _platformChannel = MethodChannel('native_channel');
    try {
      await _platformChannel
          .invokeMethod('sendStringToNative', {'message': message});
    } catch (e) {
      print('Error sending string to native: $e');
    }
  }
}
