package friendlyrobot.nyc.architecture.mvrx.viewmodel

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import friendlyrobot.nyc.architecture.mvrx.BuildConfig

abstract class MvRxViewModel<S : MvRxState>(
    initialState: S
) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)