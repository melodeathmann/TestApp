package by.life.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.life.testapp.fragments.ListFragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.OkHttp3Downloader
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
        val built = builder.build()
        Picasso.setSingletonInstance(built)

        FragmentUtils.changeFragment(this, R.id.contentFrame, ListFragment())
    }
}
