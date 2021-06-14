package com.mycelium.giftbox

import android.content.Context
import android.content.SharedPreferences
import com.mycelium.giftbox.client.models.Order
import com.mycelium.wallet.WalletApplication


object GiftboxPreference {
    private val preference: SharedPreferences by lazy { WalletApplication.getInstance().getSharedPreferences("giftbox_main", Context.MODE_PRIVATE) }
    const val REDEEMED_KEY = "redeemed_set"
    const val DELETE_KEY = "delete_set"

    fun redeem(order: Order) {
        val redeemSet = preference.getStringSet(REDEEMED_KEY, setOf())!!.toMutableSet()
        redeemSet.add(order.clientOrderId)
        preference.edit().putStringSet(REDEEMED_KEY, redeemSet).apply()
    }

    fun isRedeemed(order: Order) =
            preference.getStringSet(REDEEMED_KEY, setOf())!!.contains(order.clientOrderId)

    fun remove(order: Order) {
        val redeemSet = preference.getStringSet(DELETE_KEY, setOf())!!.toMutableSet()
        redeemSet.add(order.clientOrderId)
        preference.edit().putStringSet(DELETE_KEY, redeemSet).apply()
    }

    fun isRemoved(order: Order) =
            preference.getStringSet(DELETE_KEY, setOf())!!.contains(order.clientOrderId)

    fun setGroupOpen(group: String, flag: Boolean) {
        preference.edit().putBoolean(group, flag).apply()
    }

    fun isGroupOpen(group: String): Boolean =
            preference.getBoolean(group, true)
}