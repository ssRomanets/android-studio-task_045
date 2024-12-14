package com.example.task_045.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task_045.R
import com.example.task_045.databinding.FragmentWeatherBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        binding.imageViewIV.setImageResource(R.drawable.moscow)
        binding.cityCaptionET.setText(R.string.city)

        binding.updateBTN.setOnClickListener{
            getCurrentWeather()
        }
    }

    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(binding.cityCaptionET.text.toString(), "metric", getString(R.string.app_key))
            } catch (e: IOException) {
                Toast.makeText(context, "app error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(context, "http error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()

                    binding.feelsLikeTV.text = "${data!!.main.feels_like}"
                    binding.grndLevelTV.text = "${data!!.main.grnd_level}"
                    binding.humidityTV.text  = "${data!!.main.humidity} %"
                    binding.pressureTV.text  = "${data!!.main.pressure} mm hc"
                    binding.seaLevelTV.text  = "${data!!.main.sea_level} m"
                    binding.tempTV.text      = "${data!!.main.temp} deg C"
                    binding.tempMaxTV.text   = "${data!!.main.temp_max} deg C"
                    binding.tempMinTV.text   = "${data!!.main.temp_min} deg C"
                }
            }
        }
    }
}


