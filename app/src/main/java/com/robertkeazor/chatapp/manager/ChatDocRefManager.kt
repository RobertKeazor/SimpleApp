
package com.robertkeazor.chatapp.manager

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.robertkeazor.chatapp.model.Message
import io.reactivex.Observable
import javax.inject.Inject

class ChatDocRefManager @Inject constructor() {
    val chatDocKey = "Chat"
    val chatDofRef = FirebaseFirestore
        .getInstance()
        .collection(chatDocKey)

    fun sendMessage(message: Message) = Observable.create<Message> {
        chatDofRef
            .document()
            .set(message)
            .addOnSuccessListener { aVoid ->
                Log.v("Messgae", "MessageSent")
                it.onNext(message)
            }.addOnFailureListener { e -> it.onError(e) }
    }

    fun retreiveChat() = Observable.create<List<Message>> {
        chatDofRef.get().addOnSuccessListener { querySnapshot ->
            it.onNext(querySnapshot.toObjects<Message>(Message::class.java))
        }.addOnFailureListener { e -> it.onError(e) }
    }

    fun chatUpdate() = Observable.create<Message> {
        chatDofRef.addSnapshotListener ({ snapshot, firebaseException ->
            if (firebaseException != null) {
                it.onError(firebaseException)
            } else {
                for (dc in snapshot!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> it.onNext(dc.document.toObject<Message>(Message::class.java))
                        DocumentChange.Type.MODIFIED -> TODO()
                        DocumentChange.Type.REMOVED -> TODO()
                    }
                }
            }

        })

    }
}
