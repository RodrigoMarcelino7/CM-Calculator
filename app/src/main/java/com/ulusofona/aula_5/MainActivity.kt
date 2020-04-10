package com.ulusofona.aula_5

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.fragment_calculator.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = MainActivity::class.java.simpleName
    private val VISOR_KEY = "visor"

    private var historic = arrayListOf(Operation("1+1","2"), Operation("2+3","5"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG,"O metodo onCreate foi invocado")

        setSupportActionBar(toolbar)
        setupDrawerMenu()

        NavigationManager.goToCalculatorFragment(supportFragmentManager)
    }

    override fun onDestroy() {
        Log.i(TAG, "O metodo onDestroy foi invocado")
        super.onDestroy();
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_calculator -> NavigationManager.goToCalculatorFragment(supportFragmentManager)
            R.id.nav_history -> NavigationManager.goToHistoricFragment(supportFragmentManager)
            R.id.nav_logout ->{
                user = null
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true;
    }

    private fun setupDrawerMenu() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener(this)
        nav_drawer.getHeaderView(0).name.text = user?.username
        nav_drawer.getHeaderView(0).email.text = user?.email
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        if(supportFragmentManager.backStackEntryCount == 1) finish()
        super.onBackPressed()
    }
}