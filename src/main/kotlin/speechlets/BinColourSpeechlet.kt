package speechlets

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

class BinColourSpeechlet : Speechlet {
    override fun onSessionStarted(request: SessionStartedRequest?, session: Session?) {    }

    override fun onSessionEnded(request: SessionEndedRequest?, session: Session?) {    }

    @Throws(SpeechletException::class)
    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        val intent = request.intent
        val intentName = intent?.name

        if ("BinColourIntent" == intentName) {
            return binColourResponse()
        } else {
            throw SpeechletException("Invalid Intent")
        }
    }

    @Throws(SpeechletException::class)
    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        return binColourResponse()
    }

    private fun binColourResponse(): SpeechletResponse {

        val weekNumber = weekOfYear()
        var binColourOutput = ""

        // black
        if (weekNumber % 2 == 1) {
            binColourOutput = "This week's bin colour is orange and green"
        } else if (weekNumber % 2 == 0) {
            binColourOutput = "This week's bin colour is black"
        }

        val speechText = "Welcome to the bin colour bot. $binColourOutput"
        val card = SimpleCard()
        card.title = "BinColour"
        card.content = speechText
        val speech = PlainTextOutputSpeech()
        speech.text = speechText

        return SpeechletResponse.newTellResponse(speech, card)
    }

    private fun weekOfYear(): Int {
        val date = LocalDate.now()
        val weekFields = WeekFields.of(Locale.getDefault())
        return date.get(weekFields.weekOfWeekBasedYear())
    }
}
