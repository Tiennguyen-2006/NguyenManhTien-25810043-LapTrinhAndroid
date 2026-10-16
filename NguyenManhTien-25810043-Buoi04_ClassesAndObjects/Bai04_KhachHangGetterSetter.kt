//25810043
//Nguyen Manh Tien
package bai04

class KhachHang {
    var ho: String = ""
    var ten: String = ""

    var hoTen: String
        get() = "$ho $ten"
        set(value) {
            val parts = value.split(" ", limit = 2)
            if (parts.isNotEmpty()) {
                ho = parts[0]
                ten = if (parts.size > 1) parts[1] else ""
            }
        }
}

fun main() {
    val kh = KhachHang()
    kh.ho = "Nguyen"
    kh.ten = "Quang Nhat"
    
    println("Ho ten ban dau: ${kh.hoTen}")
    
    // Đổi giá trị tên
    kh.ten = "Van A"
    println("Ho ten sau khi doi ten: ${kh.hoTen}")
    
    // Gán chuỗi họ tên mới
    kh.hoTen = "Le Thi B"
    println("Sau khi gan hoTen moi -> Ho: ${kh.ho}, Ten: ${kh.ten}")
}
