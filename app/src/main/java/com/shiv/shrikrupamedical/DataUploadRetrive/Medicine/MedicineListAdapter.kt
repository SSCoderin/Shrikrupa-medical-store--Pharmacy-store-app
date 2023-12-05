package com.shiv.shrikrupamedical.DataUploadRetrive.Medicine
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shiv.shrikrupamedical.R

class MedicineListAdapter(private var medicineList: List<MedicineDetails>) :
    RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {

    inner class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicineNameTextView: TextView = itemView.findViewById(R.id.medicineNameTextView)
        val personNameTextView: TextView = itemView.findViewById(R.id.personNameTextView)
        val phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumberTextView)
        val medicineImageView: ImageView = itemView.findViewById(R.id.medicineImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val currentMedicine = medicineList[position]
        holder.medicineNameTextView.text = currentMedicine.name
        holder.personNameTextView.text = currentMedicine.personName
        holder.phoneNumberTextView.text = currentMedicine.phoneNumber

        // Check if imageUrl is not empty
        Log.d("ImageUrl", "Url: ${currentMedicine.imageUrl}")

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(currentMedicine.imageUrl)
            .into(holder.medicineImageView)
    }


    override fun getItemCount(): Int {
        return medicineList.size
    }

    fun updateMedicineList(newList: List<MedicineDetails>) {
        medicineList = newList
        notifyDataSetChanged()
    }
}
