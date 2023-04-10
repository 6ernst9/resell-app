package com.example.supplyr

class Account(name: String, email : String, pass: String, adress : String, phone : String, username : String,
seller : Double, itemsPurchased : Int, itemsReselled : Int) {
    var name : String = ""
    var email : String = ""
    var pass : String = ""
    var adress : String = ""
    var phone : String = ""
    var username : String = ""
    var seller : Double = 0.0
    var itemsPurchased : Int = 0
    var itemsReselled : Int = 0

    init {
        this.name = name
        this.email = email
        this.pass = pass
        this.seller = seller
        this.phone = phone
        this.username = username
        this.adress = adress
        this.itemsPurchased = itemsPurchased
        this.itemsReselled = itemsReselled


    }
}