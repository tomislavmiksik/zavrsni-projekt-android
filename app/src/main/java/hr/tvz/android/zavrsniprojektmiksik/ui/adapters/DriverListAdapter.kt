package hr.tvz.android.zavrsniprojektmiksik.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity

import com.facebook.drawee.view.SimpleDraweeView




class DriverListAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val drivers: List<DriverEntity>
) : ArrayAdapter<DriverEntity>(context, layoutResource, drivers) {



    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.driver_card, parent, false)


        val driver = drivers[position]
        val uri: Uri =
            Uri.parse(driver.image)
        val draweeView = itemView.findViewById(R.id.item_image) as SimpleDraweeView
        draweeView.setImageURI(uri)


        val textView = itemView.findViewById<TextView>(R.id.text1)
        textView.text = driver.driver_name

        val text2View = itemView.findViewById<TextView>(R.id.text2)
        text2View.text = driver.position


        return itemView
    }

}