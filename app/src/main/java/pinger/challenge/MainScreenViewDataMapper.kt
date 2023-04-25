package pinger.challenge

import pinger.challenge.parsing.ApacheLogParser
import pinger.challenge.utility.PageSequenceCalculator
import javax.inject.Inject

class MainScreenViewDataMapper @Inject constructor(
    private val pageSequenceCalculator: PageSequenceCalculator
) {
    private val numberOfConsecutivePages = 3

    fun buildScreen(items: MutableList<String>): List<MainScreenItem> {
        val viewData = mutableListOf<MainScreenItem>()

        val logs = parseApacheLogs(items)
        val dataProcessed = pageSequenceCalculator.getMostCommonPageSequences(logs, numberOfConsecutivePages)
        viewData.addAll(dataProcessed.map { data ->
            MainScreenItem.MainScreenTodoItem(
                data.second,
                data.first
            )
        })
        return viewData
    }

    private fun parseApacheLogs(pathSequenceData: MutableList<String>): HashMap<String, MutableList<String>> {
        return ApacheLogParser(pathSequenceData).parseLogsForEachUser()
    }
}