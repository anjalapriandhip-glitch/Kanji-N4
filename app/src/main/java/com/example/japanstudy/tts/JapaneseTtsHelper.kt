package com.example.japanstudy.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class JapaneseTtsHelper(context: Context) : TextToSpeech.OnInitListener {

  private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
  private var isInitialized = false

  override fun onInit(status: Int) {
    if (status == TextToSpeech.SUCCESS) {
      val result = tts?.setLanguage(Locale.JAPANESE)
      if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
        // Fallback to general JAPAN locale
        tts?.setLanguage(Locale.JAPAN)
        Log.w("JapaneseTts", "Japanese language data might be missing or fallback needed.")
      }
      tts?.setSpeechRate(0.85f) // Slightly slower for language learners to hear clearly
      tts?.setPitch(1.0f)
      isInitialized = true
    } else {
      Log.e("JapaneseTts", "TTS Initialization failed: $status")
    }
  }

  fun speak(text: String) {
    if (isInitialized && tts != null) {
      tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "JapanStudyTTS_${System.currentTimeMillis()}")
    }
  }

  fun shutdown() {
    try {
      tts?.stop()
      tts?.shutdown()
      tts = null
      isInitialized = false
    } catch (e: Exception) {
      Log.e("JapaneseTts", "Error shutting down TTS", e)
    }
  }
}
