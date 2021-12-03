package com.example.zadanie.ui.viewModels

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.doAsync
import org.stellar.sdk.*
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.SubmitTransactionResponse
import java.lang.Exception


class TransferViewModel(private val repository: DataRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>>
        get() = repository.getContacts(repository.getLoggedUser().getAccId())

    val destinationInput: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()
    val notification: MutableLiveData<String> = MutableLiveData()
    val listc: MutableList<Contact> = ArrayList()

    val contactsAsList: LiveData<List<String>> = Transformations.map(contacts) {
        val text: MutableList<String> = ArrayList()
        text.add("Vyber prijimatela")
        it?.let {
            it.forEach {
                text.add(it.name)
                listc.add(it)
            }
        }
        text
    }

    fun makeTransfer() {
        doAsync {
            try {
                notification.postValue("")

                val source =
                    KeyPair.fromSecretSeed(
                        repository.getAccountById(
                            repository.getLoggedUser().getAccId()
                        ).private_key
                    )
                val destination =
                    KeyPair.fromAccountId(destinationInput.value)

                println(destinationInput.value)

                val server = Server("https://horizon-testnet.stellar.org")

                val sourceAccount: AccountResponse = server.accounts().account(source.accountId)

                val transfer: Transaction = Transaction.Builder(sourceAccount, Network.TESTNET)
                    .addOperation(
                        PaymentOperation.Builder(
                            destination.accountId,
                            AssetTypeNative(),
                            amount.value
                        ).build()
                    )
                    .addMemo(Memo.text("Test Transaction"))
                    .setTimeout(180)
                    .setBaseFee(Transaction.MIN_BASE_FEE)
                    .build()

                if (pin.value == repository.getLoggedUser().getPin()) {
                    transfer.sign(source)

                    try {
                        val response: SubmitTransactionResponse = server.submitTransaction(transfer)
                        notification.postValue("Success")
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
            } catch (e: Exception) {
                println("Wrong destination!")
                notification.postValue("Wrong destination!")
            }
        }
    }

    fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        listc.let {
            it.forEach {
                if (it.name == parent?.selectedItem) {
                    destinationInput.postValue(it.public_key_reciever)
                    return
                }
                else {
                    destinationInput.postValue("")
                }
            }
        }

    }

}


    //pos                                 get selected item position
    //view.getText()                      get lable of selected item
    //parent.getAdapter().getItem(pos)    get item by pos
    //parent.getAdapter().getCount()      get item count
    //parent.getCount()                   get item count
    //parent.getSelectedItem()            get selected item
    //and other...

