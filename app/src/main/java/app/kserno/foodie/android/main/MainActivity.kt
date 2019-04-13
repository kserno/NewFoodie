package app.kserno.foodie.android.main

import android.app.PendingIntent
import android.content.Context
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import app.kserno.foodie.android.R
import app.kserno.foodie.android.di.DaggerMainComponent
import app.kserno.foodie.android.di.MainComponent
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.model.FoodOrder
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.content.IntentFilter.MalformedMimeTypeException
import android.content.Intent
import android.content.IntentFilter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import androidx.navigation.NavController
import app.kserno.foodie.android.di.MainModule
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.FoodWs
import com.squareup.moshi.Moshi
import java.util.Arrays.asList


/**
 *  Created by filipsollar on 2019-03-27
 */
class MainActivity: AppCompatActivity() {

    //var currentOrder : List<FoodOrder>

    companion object {
        const val MIME_TEXT_PLAIN = "text/plain"
    }

    lateinit var socket: WsService

    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    lateinit var component: MainComponent

    var controller: NavController? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //newOrderSubject.onNext(null)

        controller = findNavController(R.id.navHostFragment)


        component = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .build()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        handleIntent(intent)
        //AppBarConfiguration(resources.getMe, drawerLayout)
    }

    private fun handleIntent(intent: Intent) {
        val action = intent.action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == action) {

            val type = intent.type
            if (MIME_TEXT_PLAIN == type) {

                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                //NdefReaderTask().execute(tag) TODO

            } else {
                Log.d("NFC", "Wrong mime type: $type")
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED == action) {

            // In case we would still use the Tech Discovered Intent
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            val techList = tag.techList
            val searchedTech = Ndef::class.java.name

            for (tech in techList) {
                if (searchedTech == tech) {
                    //NdefReaderTask().execute(tag) TODO
                    break
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val filters = arrayOfNulls<IntentFilter>(1)
        val techList = arrayOf<Array<String>>()

        // Notice that this is the same filter as in our manifest.
        filters[0] = IntentFilter()
        filters[0]?.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED)
        filters[0]?.addCategory(Intent.CATEGORY_DEFAULT)
        try {
            filters[0]?.addDataType(MIME_TEXT_PLAIN)
        } catch (e: MalformedMimeTypeException) {
            throw RuntimeException("Check your mime type.")
        }


        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, filters, techList)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

}