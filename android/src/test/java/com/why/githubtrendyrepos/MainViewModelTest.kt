package com.why.githubtrendyrepos

import androidx.lifecycle.ViewModel
import com.github.whyrising.y.concretions.map.m
import com.why.githubtrendyrepos.viewmodels.MainViewModel
import com.why.githubtrendyrepos.viewmodels.NavigationItemViewModel
import com.why.githubtrendyrepos.viewmodels.Pages.SETTINGS
import com.why.githubtrendyrepos.viewmodels.Pages.TRENDING
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.reflection.shouldBeSubtypeOf
import io.kotest.matchers.shouldBe

class MainViewModelTest : FreeSpec(
    {
        "ctor" {
            val item = NavigationItemViewModel(TRENDING, true) { }
            val defaultItems = m(
                TRENDING to item,
                SETTINGS to NavigationItemViewModel(SETTINGS) {}
            )
            val vm = MainViewModel()
            val oldSelectedPage = vm.currentlySelectedPage

            vm::class.shouldBeSubtypeOf<ViewModel>()
            vm.isDarkTheme.shouldBeFalse()
            vm.navigationItems shouldBe defaultItems
            oldSelectedPage shouldBe TRENDING
            vm.navigationItems(oldSelectedPage)!!.isSelected.shouldBeTrue()
        }

        "select(navigationItem) should select() the navigationItem passed" {
            val vm = MainViewModel()
            val toBeSelectedItem = vm.navigationItems(SETTINGS)!!
            val oldSelectedPage = vm.currentlySelectedPage

            vm.onSelect(toBeSelectedItem)

            vm.currentlySelectedPage shouldBe toBeSelectedItem.page
            vm.navigationItems(oldSelectedPage)!!.isSelected.shouldBeFalse()
        }

        "defaultItems should be wired with select()" {
            val vm = MainViewModel()
            val toBeSelectedItem = vm.navigationItems(SETTINGS)!!
            val oldSelectedPage = vm.currentlySelectedPage

            toBeSelectedItem.select()

            vm.currentlySelectedPage shouldBe toBeSelectedItem.page
            vm.navigationItems(oldSelectedPage)!!.isSelected.shouldBeFalse()
        }

        "darkThemeOn()" {
            val vm = MainViewModel()

            vm.darkThemeOn()

            vm.isDarkTheme.shouldBeTrue()
        }

        "darkThemeOff()" {
            val vm = MainViewModel()

            vm.darkThemeOff()

            vm.isDarkTheme.shouldBeFalse()
        }
    }
)