package com.nytimes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nytimes.R
import com.nytimes.data.AdapterNYTimes
import com.nytimes.data.response.GetMasters
import com.nytimes.databinding.ActivityMainBinding
import com.nytimes.support.SharedPreference_class
import com.nytimes.support.Utility
import com.nytimes.support.toast
import com.nytimes.ui.description.DescriptionActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class MainActivity : AppCompatActivity() , KodeinAware,AdapterNYTimes.OnClickAction,AdapterNYTimes.OnItemClick {

    override val kodein by kodein()
    private val factory : MainViewModelFactory by instance()
    private lateinit var binding : ActivityMainBinding

    lateinit var spfs : SharedPreference_class

    var masters  : GetMasters? = null
    var mastersList  = ArrayList<GetMasters.Result>()


    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        spfs = SharedPreference_class(this)

        viewBind()


    }

    fun viewBind()
    {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        //setLiveDataObservers()
        binding.adapter = AdapterNYTimes(this, mastersList,this,this)


        setObserver()
       // setOnClickListner()
        getMasterData()


    }

    fun getMasterData()
    {


        if (Utility.isNetworkAvailable(this)) {
            Utility.showProgressDialog(this)

           viewModel.GetMastere()


        } else {
            this.toast(this.resources.getString(R.string.no_internet)).toString()
            Utility.hideProgressDialog()
        }
    }

    /**  Api Response Observer*/
    private fun setObserver() {

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                Utility.showProgressDialog(this)
            } else {
                Utility.hideProgressDialog()
            }
        })



        viewModel.getException().observe(this, Observer { exception ->
            this.toast(getString(exception))
        })
        viewModel.getError().observe(this, Observer { error ->
            this.toast(error)
        })

        viewModel.getMaster().observe(this, Observer { result ->
            if (result.status == "OK") {
                masters = result
                mastersList.addAll(result.results)
                binding.adapter?.notifyDataSetChanged()

                // UpdateUi()

            }
            else  {
                Utility.hideProgressDialog()
                toast("Something went wrong.")

                Utility.hideProgressDialog()
            }
        })




    }

    override fun onClickAction(position: Int, item: ArrayList<GetMasters.Result>) {

        spfs.save("image",item[position].media[0].mediametadata[2].url)
        spfs.save("title",item[position].title)
        spfs.save("abstract",item[position].abstract)
        spfs.save("type",item[position].type)
        spfs.save("published_date",item[position].published_date)
        spfs.save("section",item[position].section)
        spfs.save("byline",item[position].byline)


        spfs.save("caption",item[position].media[0].caption)
        spfs.save("copyright",item[position].media[0].copyright)


        Intent(this, DescriptionActivity::class.java).also {
            // it.putExtra("position",patientId)
            startActivity(it)
        }

    }

    override fun onItemClick(position: Int, value: TextView) {
        Intent(this, DescriptionActivity::class.java).also {
            // it.putExtra("position",patientId)
            startActivity(it)
        }

    }


}