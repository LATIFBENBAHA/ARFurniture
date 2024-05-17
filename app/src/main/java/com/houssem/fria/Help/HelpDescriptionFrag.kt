package com.example.sandy.kotlinfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.houssem.fria.LoginActivity
import com.houssem.fria.OpenCameraActivity
import com.houssem.fria.R
import kotlinx.android.synthetic.main.app_technical_description_frag.view.*
class Utils{
    companion object {
        const val TOKEN = "my_token_value"
        const val EXPIRY_TIME = 24 * 60 * 60 * 1000 // 24 hours in milliseconds
    }
}

class HelpDescriptionFrag() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //
        var v: View = inflater.inflate(R.layout.app_description_frag, container, false)


        // set up next button
        v.exit_greeting.setOnClickListener {

            val token = activity?.getSharedPreferences("my_app", Context.MODE_PRIVATE)?.getString(Utils.TOKEN, null)
            val expiryTime = activity?.getSharedPreferences("my_app", Context.MODE_PRIVATE)?.getLong(
                Utils.EXPIRY_TIME.toString(), 0)
            if (token != null && expiryTime!=null && System.currentTimeMillis() < expiryTime) {
                // Token is still valid, take user to the next activity
                val intent = Intent(requireActivity(), OpenCameraActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else{
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            //activity!!.finish()
            /*val intent = Intent(activity!!, OpenCameraActivity::class.java)
            startActivity(intent)
            activity!!.finish()*/
        }

        // set up video 1 button
        v.textured_surfaces_cv.setOnClickListener {
            var fManager = activity?.supportFragmentManager

            var tx = fManager!!.beginTransaction()
            tx.add(
                R.id.frag, HelpVideoFrag(
                    R.raw.video_4, false
                )
            )
            tx.commit()

        }

        // set up video 2 button
        v.lighting_cv.setOnClickListener {
            var fManager = activity?.supportFragmentManager

            var tx = fManager!!.beginTransaction()
            tx.add(
                R.id.frag, HelpVideoFrag(
                    R.raw.video_5, false
                )
            )
            tx.commit()

        }

        // set up video 3 button
        v.moving_cv.setOnClickListener {
            var fManager = activity?.supportFragmentManager

            var tx = fManager!!.beginTransaction()
            tx.add(
                R.id.frag, HelpVideoFrag(
                    R.raw.video_6, false
                )
            )
            tx.commit()

        }

        return v

    }


}