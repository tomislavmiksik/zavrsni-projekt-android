package hr.tvz.android.zavrsniprojektmiksik.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import hr.tvz.android.zavrsniprojektmiksik.R

import com.facebook.drawee.view.SimpleDraweeView
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity

class TeamListAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val teams: List<TeamEntity>
) : ArrayAdapter<TeamEntity>(context, layoutResource, teams) {



    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.driver_card, parent, false)


        val team = teams[position]
        val uri: Uri =
            Uri.parse(team.image)
        val draweeView = itemView.findViewById(R.id.item_image) as SimpleDraweeView
        draweeView.setImageURI(uri)


        val textView = itemView.findViewById<TextView>(R.id.text1)
        textView.text = team.team_name

        val text2View = itemView.findViewById<TextView>(R.id.text2)
        text2View.text = team.position


        return itemView
    }

}