package com.example.nguyenmanhtien_25810043_buoi02_kotlinbasics

//25810043
//Nguyen Manh Tien
fun main() {
    val sL: Int = 12
    val DG: Double = 45000.0
    val Vat: Double = 0.08
    val tienHang: Double = sL.toDouble() * DG
    val tongTien: Double = tienHang * (1 + Vat)

    println("Tong tien thanh toan: $tongTien VND")
}
main()