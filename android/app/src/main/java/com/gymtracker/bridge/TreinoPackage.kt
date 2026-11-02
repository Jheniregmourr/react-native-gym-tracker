package com.gymtracker.bridge

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

//O package fala do MODULO como um TODO, entao eu nao preciso alterar ele a cada funcao que eu adc

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
            // 1	"TreinoModule"	O nome do módulo
            // 2	"TreinoModule"	O nome da classe
            // 3	false	Pode sobrescrever um módulo existente? Não
            // 4	false	Precisa iniciar junto com o app? Não, só quando for usado
            // 5	false	É um módulo em C++? Não, é Kotlin
            // 6	true	É um TurboModule? Sim
        )
    }
}