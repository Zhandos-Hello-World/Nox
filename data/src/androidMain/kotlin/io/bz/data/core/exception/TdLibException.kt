package io.bz.data.core.exception

import io.bz.data.lib.TdApi

class TdLibException(val error: TdApi.Error) : Exception("TDLib error ${error.code}: ${error.message}")