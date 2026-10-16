//25810043
//Nguyen Manh Tien
package bai01

class SanPham(val tenSanPham: String, val gia: Double, val soLuongTonKho: Int = 0)

fun main() {
    val sp1 = SanPham("Laptop", 1500.0, 10)
    val sp2 = SanPham(tenSanPham = "Chuot Khong Day", gia = 20.0)

    println("San pham 1: Ten = ${sp1.tenSanPham}, Gia = ${sp1.gia}, So luong ton kho = ${sp1.soLuongTonKho}")
    println("San pham 2: Ten = ${sp2.tenSanPham}, Gia = ${sp2.gia}, So luong ton kho = ${sp2.soLuongTonKho}")
}
