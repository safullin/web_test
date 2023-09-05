import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:multi_scanner/multi_scanner.dart';

import 'home_state.dart';
import 'main.dart';
import 'native.dart';

class HomeCubit extends Cubit<HomeState> implements MultiScannerDelegate {
  HomeCubit() : super(const HomeState.loading());


  final MultiScanner getAccountUseCase = getIt<MultiScanner>();

  void initScanner(){
    getAccountUseCase.addDelegate(this);
  }

  @override
  bool? onScanEvent(String payload) {
    NativeChannel.sendStringToNative(payload);
  }

  @override
  bool? onErrorScan(Exception error) {
    // TODO: implement onErrorScan
    throw UnimplementedError();
  }

  @override
  Future<void> close() {
    getAccountUseCase.removeDelegate(this);
    return super.close();
  }

}
