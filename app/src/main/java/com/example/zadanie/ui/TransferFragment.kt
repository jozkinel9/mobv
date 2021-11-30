package com.example.zadanie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie.R
import com.example.zadanie.ui.viewModels.TransferViewModel
import org.stellar.sdk.*
import org.stellar.sdk.responses.SubmitTransactionResponse
import org.stellar.sdk.Transaction.Builder

import org.stellar.sdk.responses.AccountResponse
import java.lang.Exception


class TransferFragment : Fragment() {

    companion object {
        fun newInstance() = TransferFragment()
    }

    private lateinit var viewModel: TransferViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transfer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun makeTransaction(){
        val server = Server("https://horizon-testnet.stellar.org")

        val source: KeyPair =
            KeyPair.fromSecretSeed("SCZANGBA5YHTNYVVV4C3U252E2B6P6F5T3U6MM63WBSBZATAQI3EBTQ4") // sem private key odosielatela - nacitat z DB
        val destination: KeyPair =
            KeyPair.fromAccountId("GA2C5RFPE6GCKMY3US5PAB6UZLKIGSPIUKSLRB6Q723BM2OARMDUYEJ5") // sem public key prijimatela - asi posielat z toho zoznamu

        // First, check to make sure that the destination account exists.

        server.accounts().account(destination.accountId)

        // If there was no error, load up-to-date information on your account.

        val sourceAccount: AccountResponse = server.accounts().account(source.accountId)

        // Start building the transaction.

        val transaction: Transaction = Builder(sourceAccount, Network.TESTNET)
            .addOperation(
                PaymentOperation.Builder(
                    destination.accountId,
                    AssetTypeNative(),
                    "10" // Sem pocet lumenov co chce poslat - tahat tiez z toho formu
                ).build()
            )
            .addMemo(Memo.text("Ak chce poslat clovek aj nejaku message k transakcii, mozeme to hodit sem"))
            .setTimeout(180)
            .setBaseFee(Transaction.MIN_BASE_FEE)
            .build()
        // Sign the transaction to prove you are actually the person sending it.
        transaction.sign(source)


        // And finally, send it off to Stellar!
        try {
            val response: SubmitTransactionResponse = server.submitTransaction(transaction)
            println("Success!")
            println(response)
        } catch (e: Exception) {
            println("Something went wrong!")
            println(e.message)
            // If the result is unknown (no response body, timeout etc.) we simply resubmit
            // already built transaction:
            // SubmitTransactionResponse response = server.submitTransaction(transaction);
        }
    }

}