package com.gymtracker.bridge

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class TreinoPackage : BaseReactPackage(){
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? 
        {
            return if(name == "TreinoModule") { 
                TreinoModule(reactContext) 
            } else { 
                null 
            }
        }

    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf(
            "TreinoModule" to ReactModuleInfo(
                "TreinoModule",
                "TreinoModule",
                false,
                false,
                false,
                true
            )
        )
    }
}