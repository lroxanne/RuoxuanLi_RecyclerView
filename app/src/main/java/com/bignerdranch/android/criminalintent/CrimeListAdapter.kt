package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding


class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        if (crime.requiresPolice != 0) {
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked! normal crime!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked! serious crime!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.callPolice.visibility = if (crime.requiresPolice == 0) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>,

) : RecyclerView.Adapter<CrimeHolder>() {

        private val TYPE_NORMAL = 0
        private val TYPE_SERIOUS = 1

    override fun onCreateViewHolder(

        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime=crimes[position]
        return if(crime.requiresPolice==0){
            TYPE_NORMAL
        }else {
            TYPE_SERIOUS
        }
    }
}
