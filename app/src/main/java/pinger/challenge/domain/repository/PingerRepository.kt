package pinger.challenge.domain.repository

import pinger.challenge.data.DataSource
import javax.inject.Inject

interface IPingerRepository {
    suspend fun getApacheLogs(): List<String>
}

class PingerRepository @Inject constructor(
    private val dataSource: DataSource
): IPingerRepository {
    override suspend fun getApacheLogs(): List<String> {
        return dataSource.getApacheLogs()
    }
}