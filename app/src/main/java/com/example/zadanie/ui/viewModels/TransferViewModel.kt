package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.doAsync
import kotlinx.coroutines.launch
import org.stellar.sdk.*
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.SubmitTransactionResponse
import java.lang.Exception
import com.example.zadanie.data.db.model.Transaction as DbTransaction


class TransferViewModel(private val repository: DataRepository) : ViewModel() {
    val destinationInput: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()
    val notification: MutableLiveData<String> = MutableLiveData()

    fun makeTransfer() {
        doAsync {
            val source =
                KeyPair.fromSecretSeed(repository.getAccountById(repository.getLoggedUser().getAccId()).private_key)
            val destination =
                KeyPair.fromAccountId(destinationInput.value)

            println(destinationInput.value)

            val server = Server("https://horizon-testnet.stellar.org")

            val sourceAccount: AccountResponse = server.accounts().account(source.accountId)

            val transfer: Transaction = Transaction.Builder(sourceAccount, Network.TESTNET)
                .addOperation(
                    PaymentOperation.Builder(destination.accountId, AssetTypeNative(), amount.value).build()
                )
                .addMemo(Memo.text("Test Transaction"))
                .setTimeout(180)
                .setBaseFee(Transaction.MIN_BASE_FEE)
                .build()

            if(pin.value == repository.getLoggedUser().getPin()) {
                transfer.sign(source)

                try {
                    val response: SubmitTransactionResponse = server.submitTransaction(transfer)
                    notification.postValue("Success")

                    viewModelScope.launch {
                        amount.value?.let { DbTransaction(public_key_sender = source.accountId, public_key_reciever = destination.accountId, amount = it) }
                            ?.let { repository.insertTransaction(it) }
                    }
                    println("Success!")
                } catch (e: Exception) {
                    println("Something went wrong!")
                    notification.postValue("Something went wrong")
                }

                repository.getBalance(source.accountId)

                pin.postValue("")
                amount.postValue("")
                destinationInput.postValue("")
            } else {
                notification.postValue("Wrong pin")
            }
        }
    }
}