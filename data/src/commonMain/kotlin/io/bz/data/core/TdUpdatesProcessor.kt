package io.bz.data.core

class TdUpdatesProcessor(
    private val handlers: List<TdUpdateHandler>,
) {

    suspend fun onNewUpdates(tdNativeObject: TdNativeObjectWrapper) {
        for (handler in handlers) {
            if (handler.handle(tdNativeObject)) {
                break
            }
        }
    }
}