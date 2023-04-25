package pinger.challenge.data

import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import javax.inject.Inject

interface DataSource {
    suspend fun getApacheLogs(): List<String>
}

class LogsDataSource @Inject constructor(): DataSource {

    var api: FileDownloadAPI? = null

    @Synchronized
    fun getPingerApi(): FileDownloadAPI {
        if (api == null) {
            api = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(FileDownloadAPI::class.java)
        }
        return api!!
    }

    override suspend fun getApacheLogs(): List<String> {
        return getLinesOfInputFromSource(
            getPingerApi().downloadApacheLogStream().source()
        )
    }

    private fun getLinesOfInputFromSource(source: BufferedSource): List<String> {
        val result = mutableListOf<String>()
        try {
            while (!source.exhausted()) {
                val line = source.readUtf8Line()
                if (line != null) {
                    result.add(line)
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return result
    }
}
