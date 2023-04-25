package pinger.challenge.domain.use_case

import pinger.challenge.domain.repository.PingerRepository
import javax.inject.Inject

interface IApacheLogsUseCase: CoroutineUseCase<List<String>, Unit>

class ApacheLogsUseCase @Inject constructor(
    private val pingerRepository: PingerRepository
): IApacheLogsUseCase {

    override suspend fun invoke(parameters: Unit?): List<String> {
        return pingerRepository.getApacheLogs()
    }
}