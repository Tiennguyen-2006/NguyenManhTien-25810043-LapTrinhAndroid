//25810043
//Nguyen Manh Tien
package bai03

class TaiKhoanNganHang(soTaiKhoan: String, soDuBanDau: Double) {
    var soDu: Double = soDuBanDau

    init {
        if (soDuBanDau < 0) {
            println("So du khong hop le")
        } else {
            println("Tạo tài khoản thành công. Số dư ban đầu: $soDuBanDau")
        }
    }
}

fun main() {
    val tk1 = TaiKhoanNganHang("123456789", 5000.0)
    val tk2 = TaiKhoanNganHang("987654321", -200.0)
}
