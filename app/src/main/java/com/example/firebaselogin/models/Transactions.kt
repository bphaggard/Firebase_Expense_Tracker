package com.example.firebaselogin.models


import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Transactions: RealmObject {
    //@PrimaryKey
    //var _id: ObjectId = ObjectId()
    //var category: String = ""
    //var amount: Double = 0.0
    //var date: String = ""
    var title: String = ""
}