package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import org.stellar.sdk.*

class TransferViewModel(private val repository: DataRepository) : ViewModel() {
    val destination: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()



    //only for testing
    val accounts: LiveData<List<Account>>
        get() = repository.getAccounts()

    val bla: LiveData<Account> = repository.getAccountById(3)

    val destinationInput: LiveData<String> = Transformations.map(accounts) {
        var text = ""
        it?.let {
            text = it[1].public_key
        }
        text
    }



    fun makeTransfer() {
        val account: LiveData<Account> = repository.getAccountById(repository.getLoggedUser().getAccId())

        amount.postValue("kurva")

        val server = Server("https://horizon-testnet.stellar.org")
        val public_key = account.value?.public_key
        val private_key = account.value?.private_key

        val sourceAccount = server.accounts().account(public_key)

        val transfer: Transaction = Transaction.Builder(sourceAccount, Network.TESTNET)
            .addOperation(
                PaymentOperation.Builder(destination.value, AssetTypeNative(), "10").build()
            )
            .addMemo(Memo.text("Test Transaction"))
            .setTimeout(180)
            .setBaseFee(Transaction.MIN_BASE_FEE)
            .build()
        transfer.sign(private_key?.toByteArray())
        repository.makeTransfer(transfer)
    }
}