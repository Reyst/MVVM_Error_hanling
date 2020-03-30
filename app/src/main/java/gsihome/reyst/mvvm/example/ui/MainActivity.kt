package gsihome.reyst.mvvm.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import gsihome.reyst.mvvm.example.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavController()
    }

    private fun setupNavController() {
        val host = nav_main_fragment as NavHostFragment
        val navController = host.navController
        navigation_bar.setupWithNavController(navController)
    }
}
