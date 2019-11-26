package com.azazellj.expandablerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_v1.*

class V1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v1)

        v1recycler.background = ElevationBackground()

        v1recycler.adapter = ExpandableAdapter().apply {
            data.addAll(
                listOf(
                    Brand(
                        name = "Brand 1",
                        image = R.mipmap.ic_launcher_round,
                        address = "address 1",
                        shops = listOf(
                            Shop("Shop 1 1", R.mipmap.ic_launcher_round, " address 1 1"),
                            Shop("Shop 1 2", R.mipmap.ic_launcher_round, " address 1 2"),
                            Shop("Shop 1 3", R.mipmap.ic_launcher_round, " address 1 3")
                        )
                    ), Brand(
                        name = "Brand 2",
                        image = R.mipmap.ic_launcher_round,
                        address = "address 2",
                        shops = listOf(
                            Shop("Shop 2 1", R.mipmap.ic_launcher_round, " address 2 1")
                        )
                    ), Brand(
                        name = "Brand 3",
                        image = R.mipmap.ic_launcher_round,
                        address = "address 3",
                        shops = listOf(
                            Shop("Shop 3 1", R.mipmap.ic_launcher_round, " address 3 1"),
                            Shop("Shop 3 2", R.mipmap.ic_launcher_round, " address 3 2"),
                            Shop("Shop 3 3", R.mipmap.ic_launcher_round, " address 3 3"),
                            Shop("Shop 3 4", R.mipmap.ic_launcher_round, " address 3 4")
                        )
                    )
                )
            )
        }
    }
}
