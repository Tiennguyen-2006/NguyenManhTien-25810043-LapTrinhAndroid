//25810043
//Nguyen Manh Tien
fun datBan(tenKhachHang: String, soLuongKhach: Int, loaiBan: String = "ban thuong") {
    println("Khach hang: $tenKhachHang, So luong: $soLuongKhach, Loai ban: $loaiBan")
}

val call1 = datBan("Nguyen Van A", 4)
val call2 = datBan("Tran Thi B", 2, "ban VIP")
val call3 = datBan(loaiBan = "ban ngoai troi", tenKhachHang = "Le Van C", soLuongKhach = 6)
