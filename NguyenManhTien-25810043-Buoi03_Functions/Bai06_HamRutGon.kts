//25810043
//Nguyen Manh Tien
fun tinhBinhPhuongDayDu(n: Int): Int {
    return n * n
}
fun tinhBinhPhuongRutGon(n: Int) = n * n
fun tinhChuViHinhVuongDayDu(canh: Double): Double {
    return canh * 4
}
fun tinhChuViHinhVuongRutGon(canh: Double) = canh * 4
fun kiemTraSoChanDayDu(n: Int): Boolean {
    return n % 2 == 0
}
fun kiemTraSoChanRutGon(n: Int) = n % 2 == 0
val res1a = tinhBinhPhuongDayDu(5)
val res1b = tinhBinhPhuongRutGon(5)
val res2a = tinhChuViHinhVuongDayDu(4.0)
val res2b = tinhChuViHinhVuongRutGon(4.0)
val res3a = kiemTraSoChanDayDu(8)
val res3b = kiemTraSoChanRutGon(8)