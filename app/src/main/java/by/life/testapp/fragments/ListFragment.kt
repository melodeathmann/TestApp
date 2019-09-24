package by.life.testapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.life.testapp.FragmentUtils
import by.life.testapp.R
import by.life.testapp.db.DataBase
import by.life.testapp.models.Product
import by.life.testapp.viewmodels.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_product.view.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)[ProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context ?: return
        viewModel.load(DataBase.get(context!!)) {
            rv.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            rv.adapter = RvAdapter(it)
        }
    }

    private inner class RvAdapter(val data: List<Product>) : RecyclerView.Adapter<RvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(layoutInflater.inflate(R.layout.item_product, parent, false))

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.init(data[position])
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

            fun init(product: Product) {
                itemView.title.text = product.name
                itemView.price.text = product.price

                if (!TextUtils.isEmpty(product.image))
                    Picasso.get()
                        .load(product.image)
                        .into(itemView.image)

                itemView.setOnClickListener {
                    FragmentUtils.changeFragment(activity, R.id.contentFrame, DetailsFragment.newInstance(product), true)
                }
            }

        }

    }

}