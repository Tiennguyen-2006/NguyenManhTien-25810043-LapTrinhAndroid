//25810043
//Nguyen Manh Tien
// Khai bao tuong minh kieu tra ve Unit
fun ghiNhatKy1(hanhDong: String): Unit {
    println("LOG: $hanhDong")
}
// Bo qua khai bao kieu tra ve Unit
fun ghiNhatKy2(hanhDong: String) {
    println("LOG: $hanhDong")
}
val call1 = ghiNhatKy1("Dang nhap he thong")
val call2 = ghiNhatKy2("Dang xuat he thong")