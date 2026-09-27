//25810043
//Nguyen Manh Tien
fun main() {
    val diem = arrayOf(8.5, 7.0, 9.5, 6.0, 5.5, 10.0, 4.5, 8.0, 9.0, 7.5)
    var tong = 0.0
    var max = diem[0]
    var min = diem[0]
    for (d in diem) {
        tong += d
        if (d > max) max = d
        if (d < min) min = d
    }
    val diemTrungBinh = tong / diem.size
    println("Diem trung binh: $diemTrungBinh")
    println("Diem cao nhat: $max")
    println("Diem thap nhat: $min")
}
main()