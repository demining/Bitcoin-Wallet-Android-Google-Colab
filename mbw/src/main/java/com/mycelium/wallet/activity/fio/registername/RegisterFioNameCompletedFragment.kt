package com.mycelium.wallet.activity.fio.registername

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.mycelium.wallet.R
import kotlinx.android.synthetic.main.fragment_register_fio_name_completed.*

class RegisterFioNameCompletedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // without this the navigation through back button would return to previous fragment (name registration and the payment)
        // but the desired behavior here is to finish the activity as we have completed the registration
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }.apply { this.isEnabled = true }
    }

    private val fioName: String by lazy {
        requireArguments().getSerializable("fioName") as String
    }
    private val fioAccountLabel: String by lazy {
        requireArguments().getSerializable("fioAccountLabel") as String
    }
    private val expirationDate: String by lazy {
        requireArguments().getSerializable("expirationDate") as String
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_register_fio_name_completed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar!!.apply {
            title = "Registration complete"
            setDisplayHomeAsUpEnabled(false)
        }
        btFinish.setOnClickListener {
            requireActivity().finish()
        }
        tvFioName.text = fioName
        tvConnectAccountsDesc.text = HtmlCompat.fromHtml(resources.getString(R.string.fio_connect_accounts_desc, fioName),
                HtmlCompat.FROM_HTML_MODE_COMPACT)
        tvConnectedFioAccount.text = fioAccountLabel
    }

    companion object {
        @JvmStatic
        fun newInstance(fioName: String, fioAccountLabel: String, expirationDate: String): RegisterFioNameCompletedFragment {
            val f = RegisterFioNameCompletedFragment()
            val args = Bundle()

            args.putString("fioName", fioName)
            args.putString("fioAccountLabel", fioAccountLabel)
            args.putString("expirationDate", expirationDate)

            f.arguments = args
            return f
        }
    }
}