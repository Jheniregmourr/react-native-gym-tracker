package com.gymtracker.bridge

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.bridge.Promise

@ReactModule(name="TreinoModule")
class TreinoModule(reactContext: ReactApplicationContext) :
    NativeTreinoModuleSpec(reactContext){
        override fun ping(promise: Promise){
            promise.resolve("pong")
        }
    }