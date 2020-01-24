package sa.revival.shortage.koin

import com.mte.baseinfrastructure.demo.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModule = module {

    viewModel { DemoViewModel(get()) }
}