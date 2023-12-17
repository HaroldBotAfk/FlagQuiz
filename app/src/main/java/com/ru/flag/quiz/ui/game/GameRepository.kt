package com.ru.flag.quiz.ui.game

import android.app.Application
import com.ru.flag.quiz.R
import com.ru.flag.quiz.data.models.FlagCard
import kotlin.random.Random

class GameRepository(context: Application) {

    private val countryArray: Array<String> = context.resources.getStringArray(R.array.countries)

    private val flagToAnswer = mutableMapOf(
        context.getString(R.string.sweden)to R.drawable.sweden,
        context.getString(R.string.china) to R.drawable.china,
        context.getString(R.string.germany) to R.drawable.germany,
        context.getString(R.string.usa) to R.drawable.usa,
        context.getString(R.string.france) to R.drawable.france,
        context.getString(R.string.russia) to R.drawable.russia,
        context.getString(R.string.austria) to R.drawable.austria,
        context.getString(R.string.colombia) to R.drawable.colombia,
        context.getString(R.string.cuba) to R.drawable.cuba,
        context.getString(R.string.czech) to R.drawable.czech,
        context.getString(R.string.egypt) to R.drawable.egypt,
        context.getString(R.string.finland) to R.drawable.finland,
        context.getString(R.string.greatBritain) to R.drawable.great_britain,
        context.getString(R.string.greece) to R.drawable.greece,
        context.getString(R.string.hungary) to R.drawable.hungary,
        context.getString(R.string.india) to R.drawable.india,
        context.getString(R.string.indonesia) to R.drawable.indonesia,
        context.getString(R.string.iran) to R.drawable.iran,
        context.getString(R.string.iraq) to R.drawable.iraq,
        context.getString(R.string.irish) to R.drawable.irish,
        context.getString(R.string.israel) to R.drawable.israel,
        context.getString(R.string.italy) to R.drawable.italy,
        context.getString(R.string.japan) to R.drawable.japan,
        context.getString(R.string.jordan) to R.drawable.jordan,
        context.getString(R.string.poland) to R.drawable.poland,
        context.getString(R.string.spain) to R.drawable.spain,
        context.getString(R.string.gdr) to R.drawable.gdr,
        context.getString(R.string.the_german_eprite) to R.drawable.german_empire,
        context.getString(R.string.the_russian_empire) to R.drawable.russian_empire,
        context.getString(R.string.argentina) to R.drawable.argentina,
        context.getString(R.string.brazil) to R.drawable.brazil,
        context.getString(R.string.england) to R.drawable.england,
        context.getString(R.string.new_zealand) to R.drawable.new_zealand,
        context.getString(R.string.nigeria) to R.drawable.nigeria,
        context.getString(R.string.pakistan) to R.drawable.pakistan,
        context.getString(R.string.ukraine) to R.drawable.ukraine,
        context.getString(R.string.vietnam) to R.drawable.vietnam,

    )

    fun createFlagCardList(): List<FlagCard> {
        val listCount = 20


        while (listCount != flagToAnswer.size) {  //Уменьшение кол-во флагов до нужного размера
            val country = countryArray[Random.nextInt(countryArray.size)]
            if (flagToAnswer.containsKey(country)) {
                flagToAnswer.remove(country)
            }
        }

        val flagCardList = mutableListOf<FlagCard>()

        flagToAnswer.forEach { (nameFlag, drawableID) ->
            val list = countryArray.filter { it != nameFlag }.shuffled() //Берем список без нужной страны и перемешиваем его


            flagCardList.add(
                FlagCard(
                    drawableID,
                    nameFlag,
                    listOf(list[0], list[1], list[2], nameFlag).shuffled()
                )
            )
        }

        return flagCardList.shuffled()
    }
}