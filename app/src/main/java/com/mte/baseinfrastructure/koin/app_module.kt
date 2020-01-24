package sa.revival.shortage.koin


import com.mte.baseinfrastructure.network.ApiService
import com.mte.baseinfrastructure.network.ShortageAPIConstant
import com.mte.infrastructurebase.data.source.remote.ApiServiceFactory
import com.mte.infrastructurebase.data.source.remote.ContextProviders
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    factory { ApiServiceFactory.getService<ApiService>(ShortageAPIConstant()) }//ApiService
    single { ContextProviders.getInstance() }//ContextProvidersd
//    single { AuthPrefStorage(get()) }//AuthPrefStorage
//    single { WelcomePrefStorage(get()) }//WelcomePrefStorage
//    single { DeviceToekPrefStorage(get()) }//AuthPrefStorage

}
