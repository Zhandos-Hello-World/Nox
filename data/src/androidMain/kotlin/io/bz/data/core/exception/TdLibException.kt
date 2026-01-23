package io.bz.data.core.exception

import org.drinkless.tdlib.TdApi

class TdLibException(val error: TdApi.Error) : Exception("TDLib error ${error.code}: ${error.message}")