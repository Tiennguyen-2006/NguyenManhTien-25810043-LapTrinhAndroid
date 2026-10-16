//25810043
//Nguyen Manh Tien
package bai05

class TaiKhoanNganHang(val soTaiKhoan: String, soDuBanDau: Double) {
    var soDu: Double = soDuBanDau

    init {
        if (soDuBanDau < 0) {
            println("So du khong hop le")
        } else {
            println("Tạo tài khoản thành công. Số dư ban đầu: $soDuBanDau")
        }
    }

    fun napTien(soTien: Double) {
        if (soTien > 0) {
            soDu += soTien
            println("Nạp thành công $soTien. Số dư mới: $soDu")
        } else {
            println("Số tiền nạp phải lớn hơn 0.")
        }
    }

    fun rutTien(soTien: Double): Boolean {
        if (soTien > 0 && soDu >= soTien) {
            soDu -= soTien
            println("Rút thành công $soTien. Số dư mới: $soDu")
            return true
        }
        println("Rút thất bại. Số dư không đủ hoặc số tiền không hợp lệ.")
        return false
    }
}

fun main() {
    val tk = TaiKhoanNganHang("123456789", 1000.0)
    
    // Gọi nạp tiền
    tk.napTien(500.0)
    println("Số dư sau khi nạp: ${tk.soDu}")
    
    // Gọi rút tiền hợp lệ
    tk.rutTien(200.0)
    println("Số dư sau khi rút hợp lệ: ${tk.soDu}")
    
    // Gọi rút tiền không hợp lệ (vượt quá số dư)
    tk.rutTien(2000.0)
    println("Số dư sau khi rút không hợp lệ: ${tk.soDu}")
}
