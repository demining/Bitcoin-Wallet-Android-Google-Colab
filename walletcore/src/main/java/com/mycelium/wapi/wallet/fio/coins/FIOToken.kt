package com.mycelium.wapi.wallet.fio.coins

import com.mycelium.wapi.wallet.Address
import com.mycelium.wapi.wallet.coins.families.EOSBasedCryptoCurrency
import com.mycelium.wapi.wallet.fio.FioAddress
import com.mycelium.wapi.wallet.fio.FioAddressData
import com.mycelium.wapi.wallet.fio.FioAddressSubtype
import fiofoundation.io.fiosdk.isFioAddress
import fiofoundation.io.fiosdk.isFioPublicKey

fun String.isFioActor(): Boolean =
        isNotEmpty()
                && length == 12
                && Regex("[.a-z1-5]+\$").matchEntire(this) != null

abstract class FIOToken : EOSBasedCryptoCurrency() {
    init {
        unitExponent = 9
        symbol = "FIO"
    }

    abstract val url: String

    override fun parseAddress(addressString: String): Address? {
        return when {
            addressString.isFioPublicKey() -> FioAddress(this, FioAddressData(addressString))
            addressString.isFioAddress() -> FioAddress(this, FioAddressData(addressString),
                    FioAddressSubtype.ADDRESS)
            addressString.isFioActor() -> FioAddress(this, FioAddressData(addressString),
                    FioAddressSubtype.ACTOR)
            else -> null
        }
    }
}