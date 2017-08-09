package speechlets

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import java.util.*


class BinColourSpeechletRequestHandler : SpeechletRequestStreamHandler(BinColourSpeechlet(), supportedApplicationIds) {
    companion object {
        private val supportedApplicationIds = HashSet<String>()

        init {
            supportedApplicationIds.add("amzn1.ask.skill.YOUR-SKILL-ID")
        }
    }
}
