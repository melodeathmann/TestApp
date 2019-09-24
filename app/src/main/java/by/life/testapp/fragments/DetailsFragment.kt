package by.life.testapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import by.life.testapp.R
import by.life.testapp.db.DataBase
import by.life.testapp.models.Product
import by.life.testapp.viewmodels.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    companion object {
        private const val ARG_PRODUCT = "ARG_PRODUCT"

        fun newInstance(data: Product) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PRODUCT, data)
            }
        }
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)[ProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>(ARG_PRODUCT)
        init(product)

        if (TextUtils.isEmpty(product?.description)) {
            context ?: return
            viewModel.details(DataBase.get(context!!), product?.product_id ?: "") {
                init(it)
            }
        }
    }

    private fun init(product: Product?) {
        title.text = product?.name
        price.text = product?.price
        description.text = product?.description

        if (!TextUtils.isEmpty(product?.image))
            Picasso.get()
                .load(product?.image)
                .into(image)
    }

}