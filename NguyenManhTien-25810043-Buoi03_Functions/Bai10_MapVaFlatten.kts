//25810043
//Nguyen Manh Tien
val danhSachSo = listOf(1, 2, 3, 4, 5)
val danhSachNhanDoi = danhSachSo.map { it * 2 }
println(danhSachNhanDoi)

val danhSachLongNhau = listOf(
    listOf(1, 2, 3),
    listOf(4, 5),
    listOf(6, 7, 8, 9)
)
val danhSachPhang = danhSachLongNhau.flatten()
println(danhSachPhang)