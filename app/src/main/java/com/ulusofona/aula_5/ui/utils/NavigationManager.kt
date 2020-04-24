package com.ulusofona.aula_5.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ulusofona.aula_5.R
import com.ulusofona.aula_5.ui.fragments.CalculatorFragment
import com.ulusofona.aula_5.ui.fragments.HistoricFragment

abstract class NavigationManager {

    companion object {

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToCalculatorFragment(fm: FragmentManager){
            placeFragment(
                fm,
                CalculatorFragment()
            )
        }

        fun goToHistoricFragment(fm: FragmentManager){
            placeFragment(
                fm,
                HistoricFragment()
            )
        }

    }

}