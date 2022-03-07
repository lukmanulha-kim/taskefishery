package com.lukman.taskefishery.data

import com.google.gson.annotations.SerializedName

data class ResponseHarga(

	@field:SerializedName("ResponseHarga")
	val responseHarga: List<ResponseHargaItem?>? = null
)

data class ResponseHargaItem(

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("komoditas")
	val komoditas: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("tgl_parsed")
	val tglParsed: String? = null,

	@field:SerializedName("area_provinsi")
	val areaProvinsi: String? = null,

	@field:SerializedName("area_kota")
	val areaKota: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class PostHarga(
	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("komoditas")
	val komoditas: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("tgl_parsed")
	val tglParsed: String? = null,

	@field:SerializedName("area_provinsi")
	val areaProvinsi: String? = null,

	@field:SerializedName("area_kota")
	val areaKota: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class QuerySeacrh(
	var search: String = ""
) {
	fun toMap(): HashMap<String, Any>{
		val query = HashMap<String, Any>()

		if (search != "") query["search"] = search
		return query
	}
}

data class ResponseData(
	@field:SerializedName("respons")
	val respons:String? = null
)

