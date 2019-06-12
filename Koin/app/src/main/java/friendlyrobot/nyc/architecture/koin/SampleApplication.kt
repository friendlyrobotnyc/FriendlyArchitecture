package friendlyrobot.nyc.architecture.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {


            androidLogger()
            androidContext(this@SampleApplication)
            modules(appModule)


        }
    }


    val appModule = module {


        // single instance of HelloRepository
        single<HelloRepository> { HelloRepositoryImpl() }

        // Simple Presenter Factory
        factory { MySimplePresenter(get()) }


        scope(named<MainActivity>()) {
            scoped { ScopedPresenter(get()) }
        }

    }


}


interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}

class MySimplePresenter(val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"
}

class ScopedPresenter(val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"
}

