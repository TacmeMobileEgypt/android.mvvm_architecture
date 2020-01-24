package sa.revival.shortage.koin


import com.mte.baseinfrastructure.demo.DemoRepo
import org.koin.dsl.module


val repoModule = module {

    single { DemoRepo(get() , get() ) }

}